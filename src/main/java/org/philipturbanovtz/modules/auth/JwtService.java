package org.philipturbanovtz.modules.auth;

import org.philipturbanovtz.dtos.api.user.UserDto;

public interface JwtService {
    String generateToken(UserDto user);

    Long extractUserId(String token);
}
