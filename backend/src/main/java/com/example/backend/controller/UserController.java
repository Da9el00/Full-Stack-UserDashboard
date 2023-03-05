package com.example.backend.controller;

import com.example.backend.persistence.UserEntity;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user")
    public UserEntity getUserById(@RequestParam Integer id){
        return userService.getUserById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users")
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("create-user")
    public ResponseEntity<Integer> addUser(HttpEntity<String> httpEntity){
        Optional<UserEntity> insertionSuccess = userService.insertNewUser(httpEntity);
        Integer userId = null;
        HttpStatus status = HttpStatus.CONFLICT;
        if(insertionSuccess.isPresent()){
            userId = insertionSuccess.get().getId();
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(userId, status);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("update-user")
    public ResponseEntity<Integer> updateUser(@RequestParam Integer id, HttpEntity<String> httpEntity){
        Optional<UserEntity> insertionSuccess = userService.updateUser(id, httpEntity);
        Integer userId = null;
        HttpStatus status = HttpStatus.CONFLICT;
        if(insertionSuccess.isPresent()){
            userId = insertionSuccess.get().getId();
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(userId, status);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("delete-user")
    public ResponseEntity<Integer> deleteUser(@RequestParam Integer id){
        Optional<UserEntity> deletionSuccess = userService.deleteUser(id);
        Integer userId = null;
        HttpStatus status = HttpStatus.CONFLICT;
        if(deletionSuccess.isPresent()){
            userId = deletionSuccess.get().getId();
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(userId, status);
    }
}

