package com.dhyenyfer.todoapi.controller;

import com.dhyenyfer.todoapi.model.User;
import com.dhyenyfer.todoapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/users")

public class UserController {

    private final UserService service;

    public UserController (UserService service) {
        this.service = service;
    }

    @PostMapping
    public User create (@RequestBody User user){
        return service.create(user);
    }

    @GetMapping
    public List <User> findAll () {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return service.findById(id);
    }

   @DeleteMapping ("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
   }
}
