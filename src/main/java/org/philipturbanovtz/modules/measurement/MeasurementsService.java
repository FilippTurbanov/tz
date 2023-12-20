package org.philipturbanovtz.modules.measurement;

import org.philipturbanovtz.database.entities.user.User;
import org.philipturbanovtz.dtos.api.measurement.rq.SaveMeasurementsRqDto;
import org.philipturbanovtz.dtos.api.measurement.rs.MeasurementsRsDto;
import org.philipturbanovtz.exceptions.measurement.InvalidMeasurementDataException;
import org.philipturbanovtz.exceptions.measurement.MeasurementNotFoundException;
import org.philipturbanovtz.exceptions.measurement.SaveMeasurementException;

public interface MeasurementsService {
    MeasurementsRsDto getLatestForUser(long userId) throws MeasurementNotFoundException;

    void saveForUser(User user, SaveMeasurementsRqDto data) throws InvalidMeasurementDataException, SaveMeasurementException;
}
