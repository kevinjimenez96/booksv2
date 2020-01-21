package dev.kevinjimenez.bookv2.controller;

import dev.kevinjimenez.bookv2.Util.JWTUtil;
import dev.kevinjimenez.bookv2.Util.JsonWebToken;
import dev.kevinjimenez.bookv2.exception.InvalidPasswordException;
import dev.kevinjimenez.bookv2.exception.InvalidTokenException;
import dev.kevinjimenez.bookv2.model.User;
import dev.kevinjimenez.bookv2.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public JsonWebToken getToken(@RequestBody User user) throws InvalidPasswordException {
        userService.checkPassword(user);
        return JWTUtil.generateToken(user.getEmail());
    }

    @PostMapping("/registration")
    public User postUser(@RequestBody User user){
        logger.info("A new user has been registered: " + user.toString());
        this.userService.insert(user);
        return user;
    }

    @GetMapping
    public List<User> getUsers(@RequestHeader("Authorization") String token) throws InvalidTokenException {
        JWTUtil.verifyToken(token);
        return this.userService.find();
    }

    @GetMapping("/{email}")
    public User getUser(@PathVariable String email, @RequestHeader("Authorization") String token){
        JWTUtil.verifyToken(token);
        return this.userService.findById(email);
    }

    @PutMapping()
    public User updateUser(@RequestBody User user, @RequestHeader("Authorization") String token){
        JWTUtil.verifyToken(token);
        this.userService.update(user);
        return user;
    }

    @DeleteMapping("/{email}")
    public void deleteUsers(@PathVariable String email, @RequestHeader("Authorization") String token){
        JWTUtil.verifyToken(token);
        this.userService.delete(email);
    }

}
