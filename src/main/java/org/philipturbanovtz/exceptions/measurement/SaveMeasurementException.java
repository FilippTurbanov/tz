package org.philipturbanovtz.exceptions.measurement;

import org.philipturbanovtz.enums.ResponseStatusCodeEnum;

public class SaveMeasurementException extends MeasurementException {

    public SaveMeasurementException(String message) {
        super(message, ResponseStatusCodeEnum.SAVE_MEASUREMENT_ERROR.getCode());
    }
}
