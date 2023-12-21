package org.philipturbanovtz.dtos.api.auth.rq;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRqDto {
    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;

    public LoginRqDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
