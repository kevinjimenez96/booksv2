package dev.kevinjimenez.bookv2.model;

import dev.kevinjimenez.bookv2.dto.UserDTO;

import java.util.Objects;

public class User {
    private String email;
    private String password;

    public User(){}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(UserDTO userDTO){
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword());
    }
}
