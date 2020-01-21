package dev.kevinjimenez.bookv2.dao.user;

import dev.kevinjimenez.bookv2.dto.UserDTO;

import java.util.List;

public interface UserDao {
    public List<UserDTO> find();

    public UserDTO findById(String email);

    public void insert(UserDTO userDTO);

    public void update(UserDTO userDTO);

    public void delete(UserDTO userDTO);

    public void delete(String email);
}
