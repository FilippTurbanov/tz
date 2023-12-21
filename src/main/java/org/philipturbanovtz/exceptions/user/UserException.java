package org.philipturbanovtz.exceptions.user;

import org.philipturbanovtz.common.ExceptionHelper;
import org.philipturbanovtz.enums.ResponseStatusCodeEnum;
import org.philipturbanovtz.exceptions.ApiException;

public class UserException extends ApiException {
    public UserException(String message, Integer code) {
        super(message, ExceptionHelper.determineErrorCode(code, ResponseStatusCodeEnum.USER_ERROR.getCode()));
    }

    public UserException(String message) {
        super(message, ResponseStatusCodeEnum.USER_ERROR.getCode());
    }
}
