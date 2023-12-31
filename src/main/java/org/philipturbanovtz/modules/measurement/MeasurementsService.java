package org.philipturbanovtz.modules.measurement;

import org.philipturbanovtz.dtos.api.measurement.rq.SaveMeasurementsRqDto;
import org.philipturbanovtz.dtos.api.measurement.rs.MeasurementsRsDto;
import org.philipturbanovtz.exceptions.measurement.InvalidMeasurementDataException;
import org.philipturbanovtz.exceptions.measurement.MeasurementNotFoundException;
import org.philipturbanovtz.exceptions.measurement.SaveMeasurementException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MeasurementsService {
    MeasurementsRsDto getLatestForUser(long userId) throws MeasurementNotFoundException;

    Page<MeasurementsRsDto> getHistoryForUser(long userId, Pageable pageable, Long dateFrom, Long dateTo);

    void saveForUser(long userId, SaveMeasurementsRqDto data) throws InvalidMeasurementDataException, SaveMeasurementException;
}
