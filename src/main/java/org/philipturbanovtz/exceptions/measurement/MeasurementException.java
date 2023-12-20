package org.philipturbanovtz.exceptions.measurement;

import org.philipturbanovtz.common.ExceptionHelper;
import org.philipturbanovtz.enums.ResponseStatusCodeEnum;
import org.philipturbanovtz.exceptions.ApiException;

public class MeasurementException extends ApiException {
    public MeasurementException(String message, Integer code) {
        super(message, ExceptionHelper.determineErrorCode(code, ResponseStatusCodeEnum.MEASUREMENT_ERROR.getCode()));
    }
}
