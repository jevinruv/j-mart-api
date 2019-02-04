package com.jevin.jmartapi.controller;

import com.jevin.jmartapi.model.Role;
import com.jevin.jmartapi.model.RoleName;
import com.jevin.jmartapi.model.User;
import com.jevin.jmartapi.model.UserSignUp;
import com.jevin.jmartapi.repository.RoleRepo;
import com.jevin.jmartapi.repository.UserRepo;
import com.jevin.jmartapi.security.JwtProvider;
import com.jevin.jmartapi.security.JwtResponse;
import com.jevin.jmartapi.security.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody User user) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignUp userSignUp){

        if(userRepo.existsByUsername(userSignUp.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username already exists"), HttpStatus.BAD_REQUEST);
        }

        if (userRepo.existsByEmail(userSignUp.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("Faild -> Email already exists"), HttpStatus.BAD_REQUEST);
        }


        // If user does not exist create account

        User user = new User(userSignUp.getName(), userSignUp.getUsername(), userSignUp.getEmail(), encoder.encode(userSignUp.getPassword()));
        Set<String> strRoles = userSignUp.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {

            switch (role){
                case "admin":
                    Role adminRole = roleRepo.findByName(RoleName.ROLE_ADMIN).orElseThrow(()-> new RuntimeException("User Role not found"));
                    roles.add(adminRole);
                    break;
                case "user":
                    Role userRole = roleRepo.findByName(RoleName.ROLE_USER).orElseThrow(()-> new RuntimeException("User role not found"));
                    roles.add(userRole);
                    break;
            }
        });

        user.setRoles(roles);
        userRepo.save(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully"), HttpStatus.OK);

    }


}
