package dev.kevinjimenez.bookv2.dao.user.implementations;

import dev.kevinjimenez.bookv2.dao.user.UserDao;
import dev.kevinjimenez.bookv2.dto.UserDTO;
import dev.kevinjimenez.bookv2.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserDaoImpl implements UserDao {
    private HashMap<String, UserDTO> userDTOs;

    public UserDaoImpl(){
        this.userDTOs = new HashMap<String, UserDTO>();
    }

    @Override
    public List<UserDTO> find() {
        return new ArrayList<UserDTO>(this.userDTOs.values());
    }

    @Override
    public UserDTO findById(String email) {
        return this.userDTOs.get(email);
    }

    @Override
    public void insert(UserDTO userDTO) {
        this.userDTOs.put(userDTO.getEmail(), userDTO);
    }

    @Override
    public void update(UserDTO userDTO) {
        this.userDTOs.remove(userDTO.getEmail(), userDTO);
    }

    @Override
    public void delete(UserDTO userDTO) {
        this.userDTOs.remove(userDTO.getEmail());
    }

    @Override
    public void delete(String email) {
        this.userDTOs.remove(email);
    }
}
