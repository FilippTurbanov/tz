package org.philipturbanovtz.modules.user;

import org.philipturbanovtz.dtos.api.user.UserDto;
import org.philipturbanovtz.exceptions.user.UserNotFoundException;

public interface UsersService {
    UserDto getUserById(Long userId) throws UserNotFoundException;

    UserDto getUserByEmail(String email) throws UserNotFoundException;
}
