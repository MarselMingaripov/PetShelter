package com.example.petshelter.controller;

import com.example.petshelter.entity.User;
import com.example.petshelter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @GetMapping("/id")
    public ResponseEntity<User> findUser(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping("/id")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok().body(userService.updateById(id, user));
    }

    @DeleteMapping("/id")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.deleteById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(userService.findAll());
    }
}
