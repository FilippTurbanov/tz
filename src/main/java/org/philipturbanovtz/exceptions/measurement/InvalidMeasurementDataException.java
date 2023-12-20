package org.philipturbanovtz.exceptions.measurement;

import org.philipturbanovtz.enums.ResponseStatusCodeEnum;

public class InvalidMeasurementDataException extends MeasurementException {

    public InvalidMeasurementDataException(String message) {
        super(message, ResponseStatusCodeEnum.INVALID_MEASUREMENT_DATA_ERROR.getCode());
    }
}
