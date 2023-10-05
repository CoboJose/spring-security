package com.security.cobo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UserService {


    private AuthService authService;

    public UserService(final AuthService authService) {
        this.authService = authService;
    }

    public record LoginDto(String user, String password) {};
    public record Credentials(String token, String refreshToken) {};

    public record User(String user, String email, String password) {};

    public record Session(String id, String user, String email) {};

    private static List<User> users = new ArrayList<>(Arrays.asList(
        new User("user1", "user1@mail.com", "pass1"), 
        new User("user2", "user2@mail.com", "pass2"), 
        new User("user3", "user3@mail.com", "pass3")
    ));

    private static List<Session> sessions = new ArrayList<>();


    public Credentials login (LoginDto loginData) {
        var loginUser = users.stream().filter(u -> u.user.equals(loginData.user)).findFirst();
        if (loginUser.isPresent() && loginUser.get().password.equals(loginData.password)) {
            User user = loginUser.get();
            var token = this.authService.getJWTToken(user);
            var refreshToken = UUID.randomUUID().toString();
        
            sessions.add(new Session(refreshToken, user.user(), user.email()));

            return new Credentials(token, refreshToken);
        }

        return null;
    }

    public void logout (String refreshToken) {
        sessions.removeIf(s -> s.id.equals(refreshToken));
    }

    public String refreshToken (String refreshToken) {
        var session = sessions.stream().filter(s -> s.id.equals(refreshToken)).findFirst();
        
        if (session.isPresent()) {
            return this.authService.getJWTToken(new User(session.get().user(), session.get().email(), ""));
        }

        return "";
    }

    public String refreshExpiredToken(String jwtToken) {
        return this.authService.refreshExpiredToken(jwtToken);
    }

}
