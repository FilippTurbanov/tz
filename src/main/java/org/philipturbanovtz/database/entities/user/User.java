package org.philipturbanovtz.database.entities.user;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;

    @Column(name = "created_at_ms", nullable = false)
    private Long createdAtMs;

    public User() {}

    public User(String email, String hashedPassword) {
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.createdAtMs = System.currentTimeMillis();
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

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Long getCreatedAtMs() {
        return createdAtMs;
    }

    public void setCreatedAtMs(Long createdAtMs) {
        this.createdAtMs = createdAtMs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return id.equals(user.id) && email.equals(user.email) && hashedPassword.equals(user.hashedPassword) && createdAtMs.equals(user.createdAtMs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, hashedPassword, createdAtMs);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email='" + email + '\'' + ", createdAtMs=" + createdAtMs + '}';
    }
}
