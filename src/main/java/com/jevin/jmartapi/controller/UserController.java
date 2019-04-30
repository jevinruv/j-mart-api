package com.jevin.jmartapi.controller;


import com.jevin.jmartapi.model.User;
import com.jevin.jmartapi.repository.UserRepo;
import com.jevin.jmartapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepo repo;

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public Optional<User> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<User> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public User add(@RequestBody User user) {
        return repo.save(user);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user) {

        User updatedUser =  userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {

        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
