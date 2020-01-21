package dev.kevinjimenez.bookv2.service.user.implementation;

import com.google.common.hash.Hashing;
import dev.kevinjimenez.bookv2.dao.user.UserDao;
import dev.kevinjimenez.bookv2.dto.UserDTO;
import dev.kevinjimenez.bookv2.exception.ExistingValueException;
import dev.kevinjimenez.bookv2.exception.InvalidPasswordException;
import dev.kevinjimenez.bookv2.exception.InvalidUserException;
import dev.kevinjimenez.bookv2.model.User;
import dev.kevinjimenez.bookv2.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> find() {
        List<UserDTO> userDTOs = this.userDao.find();
        List<User> users = new ArrayList<>();
        for (UserDTO userDTO: userDTOs) {
            User user = new User(userDTO);
            users.add(user);
        }
        return users;
    }

    @Override
    public User findById(String email) throws InvalidUserException {
        UserDTO userDTO = this.userDao.findById(email);
        if (userDTO == null){
            throw new InvalidUserException(email);
        }
        return new User(userDTO);
    }

    @Override
    public void insert(User user) throws ExistingValueException {
        if(this.userDao.findById(user.getEmail()) != null){
            throw new ExistingValueException(user.getEmail());
        }
        String password = Hashing.sha256()
                .hashString(user.getPassword(), StandardCharsets.UTF_8)
                .toString();
        user.setPassword(password);
        UserDTO userDTO = new UserDTO(user);
        this.userDao.insert(userDTO);
    }

    @Override
    public void update(User user) throws InvalidUserException {
        if(this.userDao.findById(user.getEmail()) == null){
            throw new InvalidUserException(user.getEmail());
        }
        String password = Hashing.sha256()
                .hashString(user.getPassword(), StandardCharsets.UTF_8)
                .toString();
        user.setPassword(password);
        UserDTO userDTO = new UserDTO(user);
        this.userDao.update(userDTO);
    }

    @Override
    public void delete(User user) throws InvalidUserException {
        if(this.userDao.findById(user.getEmail()) == null){
            throw new InvalidUserException(user.getEmail());
        }
        UserDTO userDTO = new UserDTO(user);
        this.userDao.delete(userDTO);
    }

    @Override
    public void delete(String email) throws InvalidUserException {
        if(this.userDao.findById(email) == null){
            throw new InvalidUserException(email);
        }
        this.userDao.delete(email);
    }

    @Override
    public boolean checkPassword(@NotNull User user) throws InvalidPasswordException {
        User userInDB = new User(userDao.findById(user.getEmail()));

        String password = Hashing.sha256()
                .hashString(user.getPassword(), StandardCharsets.UTF_8)
                .toString();

        if(!password.equals(userInDB.getPassword())){
            throw new InvalidPasswordException();
        }
        return true;
    }
}
