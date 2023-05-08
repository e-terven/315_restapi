package com.katia.spring.security.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.katia.spring.security.entities.User;
import com.katia.spring.security.model.*;
import com.katia.spring.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
public class UserRestController {

    private final UserService userService;
    private final ObjectMapper objectMapper;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserRestController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, ObjectMapper objectMapper) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value="/api/user/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> showInfoUser(Principal principal) {
        return ResponseEntity.ok(userService.findByEmail(principal.getName()));
    }

    @GetMapping(value="/api/userinfo/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> showUserInfo(Authentication authentication) {
        System.out.println("Controller called with principal: " + authentication.getName());
        return ResponseEntity.ok(userService.findByEmail(authentication.getName()));
    }

    //    @PostMapping(value = "/api/login", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//        System.out.println("I've tried to log in_1");
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getEmail(),
//                        loginRequest.getPassword()
//                )
//        );
//        System.out.println("I've tried to log in_2");
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String email = loginRequest.getEmail();
//
//        User userAuth = userService.findByEmail(email);
//        if (userAuth == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//        }
//
//        Long userId = userAuth.getId();
//
//        User user = userService.findByEmail(email);
//        UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getRoles());
//        String jwt = jwtTokenUtil.generateToken(userDTO);
//        System.out.println("I've tried to log in_3");
//        System.out.println(jwt);
//        return ResponseEntity.ok(new JwtResponse(jwt, userId));
//    }

}




