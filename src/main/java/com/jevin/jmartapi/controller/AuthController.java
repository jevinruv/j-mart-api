package com.jevin.jmartapi.controller;

import com.jevin.jmartapi.model.User;
import com.jevin.jmartapi.repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthRepo authRepo;

    @PostMapping
    public User loginUser(@RequestBody User user) {
        return authRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
