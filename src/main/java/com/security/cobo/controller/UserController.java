package com.security.cobo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.cobo.services.AuthService;

@RestController
public class UserController {

    private final AuthService authService;

    public record Login(String user, String password) {};
    public record User(String user, String email, String password) {};
    public record Credentials(String token) {};

    public UserController(final AuthService authService) {
        this.authService = authService;
    }

    private static List<User> users = new ArrayList<>(Arrays.asList(
        new User("user1", "user1@mail.com", "pass1"), 
        new User("user2", "user2@mail.com", "pass2"), 
        new User("user3", "user3@mail.com", "pass3")
    ));
    
    @PostMapping("/login")
    public Credentials login(@RequestBody Login loginData) {
        var user = users.stream().filter(u -> u.user.equals(loginData.user)).findFirst();

        if (user.isPresent() && user.get().password.equals(loginData.password)) {
            var jwt = this.authService.getJWTToken(user.get());
            return new Credentials(jwt);
        }

        return null;
    }
}
