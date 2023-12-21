package org.philipturbanovtz.common;

import java.security.Principal;
import org.philipturbanovtz.dtos.api.user.UserDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PrincipalHelper {
    public static UserDto getUser(Principal principal) {
        return (UserDto) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }
}
