package dev.kevinjimenez.bookv2.service;

import com.google.common.hash.Hashing;
import dev.kevinjimenez.bookv2.dao.user.UserDao;
import dev.kevinjimenez.bookv2.dto.UserDTO;
import dev.kevinjimenez.bookv2.exception.ExistingValueException;
import dev.kevinjimenez.bookv2.exception.InvalidPasswordException;
import dev.kevinjimenez.bookv2.exception.InvalidUserException;
import dev.kevinjimenez.bookv2.model.User;
import dev.kevinjimenez.bookv2.service.user.UserService;
import dev.kevinjimenez.bookv2.service.user.implementation.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Mock
    UserDao userDao;

    @InjectMocks
    UserService userService = new UserServiceImpl();
    
    @Test
    public void test_FindUsersReturnAllUsers(){
        List<UserDTO> userDTOs = new ArrayList<>();
        userDTOs.add(new UserDTO("a@c.c", "o"));
        userDTOs.add(new UserDTO("b@c.c", "o"));
        userDTOs.add(new UserDTO("c@c.c", "o"));

        Mockito.when(userDao.find()).thenReturn(userDTOs);

        List<User> users = userService.find();

        Assert.assertTrue(users.contains(new User("a@c.c", "o")));
        Assert.assertTrue(users.contains(new User("b@c.c", "o")));
        Assert.assertTrue(users.contains(new User("c@c.c", "o")));
    }

    @Test
    public void test_FindUsersSizeReturnAllUsers(){
        List<UserDTO> userDTOs = new ArrayList<>();
        userDTOs.add(new UserDTO("a@c.c", "o"));
        userDTOs.add(new UserDTO("b@c.c", "o"));
        userDTOs.add(new UserDTO("c@c.c", "o"));

        Mockito.when(userDao.find()).thenReturn(userDTOs);

        List<User> users = userService.find();

        Assert.assertEquals(3, users.size());
    }

    @Test
    public void test_FindNoUsersReturnEmpty(){
        List<UserDTO> userDTOs = new ArrayList<>();

        Mockito.when(userDao.find()).thenReturn(userDTOs);

        List<User> users = userService.find();

        Assert.assertEquals(0, users.size());
    }

    @Test
    public void test_FindUserReturnUser(){
        UserDTO userDTO = new UserDTO("a@c.c", "o");

        Mockito.when(userDao.findById("a@c.c")).thenReturn(userDTO);

        User user = userService.findById("a@c.c");

        Assert.assertEquals(user.getEmail(), "a@c.c");
    }

    @Test
    public void test_FindUserInvalidUserThrowInvalidUserException(){

        Mockito.when(userDao.findById("a@c.c")).thenReturn(null);

        try{
            User user = userService.findById("a@c.c");
            Assert.fail();
        }catch (InvalidUserException e){
            Assert.assertTrue(true);

        }
    }

    @Test
    public void test_InsertUserSuccess(){
        User user = new User("k@v.n", "o");

        Mockito.when(userDao.findById("k@v.n")).thenReturn(null);

        try{
            userService.insert(user);
        } catch (ExistingValueException e){
           Assert.fail();
        }
    }

    @Test
    public void test_InsertExistingUser(){
        User user = new User("k@v.n", "o");
        UserDTO userDTO = new UserDTO(user);

        Mockito.when(userDao.findById("k@v.n")).thenReturn(userDTO);

        try{
            userService.insert(user);
            Assert.fail();
        } catch (ExistingValueException e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void test_UpdateUserSuccess(){
        User user = new User("k@v.n", "o");
        UserDTO userDTO = new UserDTO(user);

        Mockito.when(userDao.findById("k@v.n")).thenReturn(userDTO);

        try{
            userService.update(user);
        } catch (ExistingValueException e){
            Assert.fail();
        }
    }

    @Test
    public void test_UpdateExistingUser(){
        User user = new User("k@v.n", "o");
        UserDTO userDTO = new UserDTO(user);

        Mockito.when(userDao.findById("k@v.n")).thenReturn(null);

        try{
            userService.update(user);
            Assert.fail();
        } catch (InvalidUserException e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void test_DeleteUserSuccess(){
        User user = new User("k@v.n", "o");
        UserDTO userDTO = new UserDTO(user);

        Mockito.when(userDao.findById("k@v.n")).thenReturn(userDTO);

        try{
            userService.delete(user);
        } catch (ExistingValueException e){
            Assert.fail();
        }
    }

    @Test
    public void test_DeleteUserFail(){
        User user = new User("k@v.n", "o");

        Mockito.when(userDao.findById("k@v.n")).thenReturn(null);

        try{
            userService.delete(user);
            Assert.fail();
        } catch (InvalidUserException e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void test_DeleteUserWithEmailSuccess(){
        User user = new User("k@v.n", "o");
        UserDTO userDTO = new UserDTO(user);

        Mockito.when(userDao.findById("k@v.n")).thenReturn(userDTO);

        try{
            userService.delete("k@v.n");
        } catch (ExistingValueException e){
            Assert.fail();
        }
    }

    @Test
    public void test_DeleteUserWithEmailFail(){
        User user = new User("k@v.n", "o");

        Mockito.when(userDao.findById("k@v.n")).thenReturn(null);

        try{
            userService.delete("k@v.n");
            Assert.fail();
        } catch (InvalidUserException e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void test_CheckValidPassword(){
        String password = Hashing.sha256()
                .hashString("o", StandardCharsets.UTF_8)
                .toString();
        User user = new User("k@v.n", "o");
        UserDTO userDTO = new UserDTO(user);
        userDTO.setPassword(password);

        Mockito.when(userDao.findById("k@v.n")).thenReturn(userDTO);

        try{
            userService.checkPassword(user);
        } catch (ExistingValueException e){
            Assert.fail();
        }
    }

    @Test
    public void test_CheckInvalidPassword(){
        String password = Hashing.sha256()
                .hashString("o", StandardCharsets.UTF_8)
                .toString();
        User user = new User("k@v.n", "p");
        UserDTO userDTO = new UserDTO(user);
        userDTO.setPassword(password);

        Mockito.when(userDao.findById("k@v.n")).thenReturn(userDTO);

        try{
            userService.checkPassword(user);
            Assert.fail();
        } catch (InvalidPasswordException e){
            Assert.assertTrue(true);
        }
    }
}
