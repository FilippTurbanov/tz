package org.philipturbanovtz.modules.auth;

import org.philipturbanovtz.dtos.api.auth.rq.LoginRqDto;
import org.philipturbanovtz.exceptions.auth.InvalidCredentialsException;

public interface AuthService {
    String login(LoginRqDto credentials) throws InvalidCredentialsException;
}
