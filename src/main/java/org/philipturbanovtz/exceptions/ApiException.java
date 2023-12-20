package org.philipturbanovtz.exceptions;

import org.philipturbanovtz.common.ExceptionHelper;
import org.philipturbanovtz.enums.ResponseStatusCodeEnum;

public class ApiException extends Throwable {
    private final int code;
    private final String message;
    private final Throwable cause;

    public ApiException(String message, Throwable cause, Integer code) {
        this.code = ExceptionHelper.determineErrorCode(code, ResponseStatusCodeEnum.ERROR.getCode());
        this.message = message;
        this.cause = cause;
    }

    public ApiException(String message, Integer code) {
        this.code = ExceptionHelper.determineErrorCode(code, ResponseStatusCodeEnum.ERROR.getCode());
        this.message = message;
        this.cause = null;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}
