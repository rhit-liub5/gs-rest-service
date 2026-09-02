package com.example.restservice;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final JsonDatabase database;
    private final AtomicLong counter = new AtomicLong();

    public GreetingController() {

        this.database = new JsonDatabase();

    }

    @GetMapping("/greeting")
    public List<Greeting> getAll() {

        return database.findAll();

    }

    @PostMapping("/greeting")
    public Greeting greetingPost(@RequestBody Greeting request) {

        return database.add(request);
    }

    @PutMapping("/greeting")
    public Greeting greetingPut(@RequestBody Greeting request) {
        return database.update(request);
    }

    @DeleteMapping("/greeting/{id}")
    public boolean delete(
            @PathVariable long id) {

        return database.delete(id);

    }
}
