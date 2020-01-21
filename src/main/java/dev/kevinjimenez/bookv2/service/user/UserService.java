package dev.kevinjimenez.bookv2.service.user;

import dev.kevinjimenez.bookv2.model.User;

import java.util.List;

public interface UserService {
    public List<User> find();

    public User findById(String email);

    public void insert(User user);

    public void update(User user);

    public void delete(User user);

    public void delete(String email);

    public boolean checkPassword(User user);
}
