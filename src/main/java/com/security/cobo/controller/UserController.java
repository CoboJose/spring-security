package com.security.cobo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.cobo.services.UserService;
import com.security.cobo.services.UserService.Credentials;
import com.security.cobo.services.UserService.LoginDto;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(
        final UserService userService
        ) {
        this.userService = userService;
    }
    
    @PostMapping("/login")
    public Credentials login(@RequestBody LoginDto loginData) {
        return this.userService.login(loginData);
    }

    @PostMapping("/logout")
    public String logout(@RequestBody Credentials credentials) {
        this.userService.logout(credentials.refreshToken());
        return "OK";
    }

    @PostMapping("/refresh")
    public Credentials refresh(@RequestBody Credentials credentials) {

        String token = this.userService.refreshToken(credentials.refreshToken());

        if (!token.isEmpty()) {
            return new Credentials(token, "");
        }

        return null;
    }

    @PostMapping("/refresh-expired")
    public Credentials refreshExpired(@RequestBody Credentials credentials) {

        String token = this.userService.refreshExpiredToken(credentials.token());

        if (!token.isEmpty()) {
            return new Credentials(token, "");
        }

        return null;
    }

}
