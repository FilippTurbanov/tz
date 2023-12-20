package org.philipturbanovtz.services.measurement;

import org.philipturbanovtz.database.entities.measurement.Measurement;
import org.philipturbanovtz.database.entities.user.User;
import org.philipturbanovtz.database.repositories.measurement.MeasurementsRepository;
import org.philipturbanovtz.dtos.api.measurement.rq.SaveMeasurementsRqDto;
import org.philipturbanovtz.dtos.api.measurement.rs.MeasurementsRsDto;
import org.philipturbanovtz.exceptions.measurement.InvalidMeasurementDataException;
import org.philipturbanovtz.exceptions.measurement.MeasurementNotFoundException;
import org.philipturbanovtz.exceptions.measurement.SaveMeasurementException;
import org.philipturbanovtz.modules.measurement.MeasurementsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MeasurementServiceImpl implements MeasurementsService {
    final MeasurementsRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(MeasurementServiceImpl.class);

    public MeasurementServiceImpl(MeasurementsRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public MeasurementsRsDto getLatestForUser(long userId) throws MeasurementNotFoundException {
        logger.debug("Getting latest measurements for user " + userId);
        Measurement measurement = repository.getFirstByUserIdOrderByIdDesc(userId);
        if (measurement == null) {
            throw new MeasurementNotFoundException("User " + userId + " hasn't saved measurements!");
        }
        logger.trace("Last measurements for user " + userId + ": " + measurement);
        return new MeasurementsRsDto(measurement);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MeasurementsRsDto> getHistoryForUser(long userId, Pageable pageable, Long dateFrom, Long dateTo) {
        Page<Measurement> result = repository.getMeasurementsHistoryForUser(dateFrom, dateTo, userId, pageable);
        return result.map(this::convertFromEntity);
    }

    @Override
    @Transactional
    public void saveForUser(User user, SaveMeasurementsRqDto data) throws InvalidMeasurementDataException, SaveMeasurementException {
        logger.debug("Going to save measurements for user " + user.getId());
        logger.trace("Incoming measurements: " + data.toString());
        SaveMeasurementsRqDto normalizedMeasurements = validateMeasurementsDataToSaveThenGetNormalized(data, user.getId());
        logger.trace("Normalized measurements: " + normalizedMeasurements.toString());

        try {
            Measurement dataToSave = new Measurement(normalizedMeasurements, user);
            logger.trace("Data to save: " + dataToSave);
            repository.save(dataToSave);
        } catch (Throwable e) {
            String errMsg = "Error while saving measurements for user " + user.getId() + "!";
            logger.error(errMsg, e);
            throw new SaveMeasurementException(errMsg);
        }

        logger.info("Measurements for user " + user.getId() + " saved successfully!");
    }

    private MeasurementsRsDto convertFromEntity(Measurement measurement) {
        return new MeasurementsRsDto(measurement);
    }

    private SaveMeasurementsRqDto validateMeasurementsDataToSaveThenGetNormalized(SaveMeasurementsRqDto data, long userId) throws InvalidMeasurementDataException {
        logger.debug("Going to validate measurements data before saving");

        if (data == null) {
            throw new InvalidMeasurementDataException("Measurements not specified!");
        }

        if (data.getGasInCubicMeters() == null || data.getHotWaterInCubicMeters() == null || data.getColdWaterInCubicMeters() == null) {
            throw new InvalidMeasurementDataException("At least one measurement not specified!");
        }

        SaveMeasurementsRqDto normalizedData = data.getNormalizedData();
        Measurement currentMeasurement = repository.getFirstByUserIdOrderByIdDesc(userId);

        if (currentMeasurement == null) {
            logger.debug("Measurements data validated successfully");
            return normalizedData;
        }

        if (normalizedData.getGasInCubicMeters() < currentMeasurement.getGasInCubicMeters()) {
            throwInvalidMeasurementDataException(currentMeasurement.getGasInCubicMeters(), normalizedData.getGasInCubicMeters(), "gas");
        }

        if (normalizedData.getHotWaterInCubicMeters() < currentMeasurement.getHotWaterInCubicMeters()) {
            throwInvalidMeasurementDataException(currentMeasurement.getHotWaterInCubicMeters(), normalizedData.getHotWaterInCubicMeters(), "hot water");
        }

        if (normalizedData.getColdWaterInCubicMeters() < currentMeasurement.getColdWaterInCubicMeters()) {
            throwInvalidMeasurementDataException(currentMeasurement.getColdWaterInCubicMeters(), normalizedData.getColdWaterInCubicMeters(), "cold water");
        }

        logger.debug("Measurements data validated successfully");
        return normalizedData;
    }

    private void throwInvalidMeasurementDataException(Double prevData, Double newData, String measurementName) throws InvalidMeasurementDataException {
        throw new InvalidMeasurementDataException("Specified measurement for " + measurementName + " is " + newData + " cubic meters, " + "which is less than the previous measurement: " + prevData + "!");
    }
}
