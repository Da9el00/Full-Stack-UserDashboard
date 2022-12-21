package com.example.backend.service;

import com.example.backend.persistence.UserEntity;
import com.example.backend.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserById(Integer id){
        return userRepository.getUserEntityById(id);
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }
}
