package org.philipturbanovtz.exceptions.auth;

import org.philipturbanovtz.common.ExceptionHelper;
import org.philipturbanovtz.enums.ResponseStatusCodeEnum;
import org.philipturbanovtz.exceptions.ApiException;

public class AuthException extends ApiException {
    public AuthException(String message, Integer code) {
        super(message, ExceptionHelper.determineErrorCode(code, ResponseStatusCodeEnum.AUTH_ERROR.getCode()));
    }
}
