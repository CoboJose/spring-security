package com.security.cobo.services;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public record Session(String id, String user) {};

    public record Login(Session session, String jwt) {};

    private static List<Login> savedLogins = new ArrayList<>();


    public void login (String user, String refreshToken) {
        Session s = new Session("", user);
        savedLogins.add(new Login(s, refreshToken));
    }

    public void logout (String user) {
        savedLogins.removeIf(l -> l.session.user.equals(user));
    }

    public void refreshToken (String user, String refreshToken) {
        Session s = new Session("", user);
        //savedLogins.add(new Login(s.id, refreshToken));

        //savedLogins.
    }

}
