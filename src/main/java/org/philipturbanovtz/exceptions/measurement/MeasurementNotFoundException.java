package org.philipturbanovtz.exceptions.measurement;

import org.philipturbanovtz.enums.ResponseStatusCodeEnum;

public class MeasurementNotFoundException extends MeasurementException {

    public MeasurementNotFoundException(String message) {
        super(message, ResponseStatusCodeEnum.MEASUREMENT_NOT_FOUND_ERROR.getCode());
    }
}
