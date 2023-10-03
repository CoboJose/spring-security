package com.security.cobo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/foo")
@CrossOrigin(origins = "*")
public class Controller {
    
    @GetMapping
    public List<Foo> getFoo() {
        var res = new ArrayList<Foo>();

        res.add(new Foo(1, "primer elemento"));
        res.add(new Foo(2, "segundo elemento"));

        return res;
    }
    
    public record Foo(int id, String name) {

    }
}
