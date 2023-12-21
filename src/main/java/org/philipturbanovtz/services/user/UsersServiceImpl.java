package org.philipturbanovtz.services.user;

import org.philipturbanovtz.database.entities.user.User;
import org.philipturbanovtz.database.repositories.user.UsersRepository;
import org.philipturbanovtz.dtos.api.user.UserDto;
import org.philipturbanovtz.exceptions.user.UserNotFoundException;
import org.philipturbanovtz.modules.user.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersServiceImpl implements UsersService {
    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    private final UsersRepository repository;

    public UsersServiceImpl(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long userId) throws UserNotFoundException {
        logger.trace("Getting user by id: " + userId);
        User user = repository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User " + userId + " not found!"));
        return new UserDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserByEmail(String email) throws UserNotFoundException {
        logger.trace("Getting user by email " + email);
        User user = repository.findByEmailIgnoreCase(email)
            .orElseThrow(() -> new UserNotFoundException("User not found!"));
        return new UserDto(user);
    }
}
