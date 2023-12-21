package org.philipturbanovtz.services.auth;

import org.philipturbanovtz.dtos.api.auth.rq.LoginRqDto;
import org.philipturbanovtz.dtos.api.user.UserDto;
import org.philipturbanovtz.exceptions.auth.InvalidCredentialsException;
import org.philipturbanovtz.exceptions.user.UserNotFoundException;
import org.philipturbanovtz.modules.auth.AuthService;
import org.philipturbanovtz.modules.auth.JwtService;
import org.philipturbanovtz.modules.user.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UsersService usersService;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, JwtService jwtService, UsersService usersService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.usersService = usersService;
    }

    @Override
    public String login(LoginRqDto credentials) throws InvalidCredentialsException {
        logger.trace("Making login by credentials");

        UserDto user;
        try {
            user = usersService.getUserByEmail(credentials.getEmail());
        } catch (UserNotFoundException e) {
            throw new InvalidCredentialsException("Invalid credentials!");
        }

        if (!passwordEncoder.matches(credentials.getPassword(), user.getHashedPassword())) {
            throw new InvalidCredentialsException("Invalid credentials!");
        }

        return jwtService.generateToken(user);
    }
}
