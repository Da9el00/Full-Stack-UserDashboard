package com.example.backend.service;

import com.example.backend.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

        System.out.println("---------------------");
        System.out.println(userRepository.getUserEntityById(1).getName());
        System.out.println("---------------------");
    }
}
