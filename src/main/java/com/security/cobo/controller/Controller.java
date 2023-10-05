package com.security.cobo.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/foo")
public class Controller {
    
    public record Foo(int id, String name) {}

    public Controller() {
    }

    @GetMapping
    public List<Foo> getFoo(Principal principal) {
        var res = new ArrayList<Foo>();

        System.out.println(principal.getName());

        res.add(new Foo(1, "primer elemento"));
        res.add(new Foo(2, "segundo elemento"));

        return res;
    }

    @PostMapping
    public void createFoo(@RequestBody Foo foo) {
        System.out.println(foo);
    }
}
