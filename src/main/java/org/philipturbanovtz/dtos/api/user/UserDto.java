package org.philipturbanovtz.dtos.api.user;

import org.philipturbanovtz.database.entities.user.User;

public class UserDto {
    private Long id;
    private String email;
    private String hashedPassword;
    private Long createdAtMs;

    public UserDto(Long id, String email, String hashedPassword, Long createdAtMs) {
        this.id = id;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.createdAtMs = createdAtMs;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.hashedPassword = user.getHashedPassword();
        this.createdAtMs = user.getCreatedAtMs();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCreatedAtMs() {
        return createdAtMs;
    }

    public void setCreatedAtMs(Long createdAtMs) {
        this.createdAtMs = createdAtMs;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
