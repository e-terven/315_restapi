package com.katia.spring.security.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.katia.spring.security.entities.User;
import com.katia.spring.security.model.UserDTO;

import com.katia.spring.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.*;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
//@RequestMapping(value = "/api/admin")
public class AdminRestController {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public AdminRestController(UserService userService, ObjectMapper objectMapper) {

        this.userService = userService;
        this.objectMapper = objectMapper;
    }


    @GetMapping(value = "/api/admin/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> showInfoAdmin(Principal principal) {
        return ResponseEntity.ok(userService.findByEmail(principal.getName()));
    }

    // --------------------------------- USER ------------------------------------------
    @GetMapping (value = "/api/admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        User user = userService.findById(id);
        System.out.println(user.getEmail().toString());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.getRoles().size();
//        String userJson = objectMapper.writeValueAsString(user);

        return ResponseEntity.ok(user);
    }

    // --------------------------------- CREATE ------------------------------------------

    @PostMapping(value = "/api/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO) {
        if (userService.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.IM_USED).build();
        }
        userService.createUser(userDTO);
        System.out.println(userDTO.getAge().toString());
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    // --------------------------------- UPDATE -------------------------------------------

    @PutMapping(value = "/api/admin/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) throws ChangeSetPersister.NotFoundException {

        String email = userDTO.getEmail();

        if (!email.equals(userService.findById(userId).getEmail()) && userService.existsByEmail(email)) {
            return ResponseEntity.status(HttpStatus.IM_USED).build();
        }

        userService.updateUser(userId, userDTO);
        System.out.println(userService.findById(userId).toString());
        System.out.println("u-p-d-a-t-e");
        return ResponseEntity.ok().build();
    }


    // --------------------------------- DELETE -------------------------------------------
    @DeleteMapping ("/api/admin/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        var deleteUser = this.userService.findById(id);

        if(deleteUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // --------------------------------- TABLE -------------------------------------------

    @GetMapping(value ="/api/admin/table", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findAll() {
        List<User> users = this.userService.findAll();

        if(users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }
}


