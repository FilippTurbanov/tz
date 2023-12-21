package org.philipturbanovtz.config;

import java.util.Collections;
import org.philipturbanovtz.dtos.api.user.UserDto;
import org.philipturbanovtz.modules.user.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaAuditing
public class CoreConfig {
    private final UsersService usersService;

    public CoreConfig(UsersService usersService) {this.usersService = usersService;}

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            try {
                UserDto user = usersService.getUserById(Long.valueOf(username));
                return new User(user.getId().toString(), null, Collections.emptyList());
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }
}
