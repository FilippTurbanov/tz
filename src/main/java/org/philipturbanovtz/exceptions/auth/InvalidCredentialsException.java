package org.philipturbanovtz.exceptions.auth;

import org.philipturbanovtz.enums.ResponseStatusCodeEnum;

public class InvalidCredentialsException extends AuthException {

    public InvalidCredentialsException(String message) {
        super(message, ResponseStatusCodeEnum.INVALID_CREDENTIALS_ERROR.getCode());
    }
}
