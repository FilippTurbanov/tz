package org.philipturbanovtz.exceptions.user;

import org.philipturbanovtz.enums.ResponseStatusCodeEnum;

public class UserNotFoundException extends UserException {

    public UserNotFoundException(String message) {
        super(message, ResponseStatusCodeEnum.USER_NOT_FOUND_ERROR.getCode());
    }
}
