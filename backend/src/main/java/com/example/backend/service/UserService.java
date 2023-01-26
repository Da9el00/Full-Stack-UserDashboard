package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.persistence.UserEntity;
import com.example.backend.persistence.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<UserEntity> insertNewUser(HttpEntity<String> user){
        Optional<UserEntity> insertedUser = Optional.empty();

        //Json input from body is turned into a user model
        Optional<User> userFromHttpBody = jsonToUserModel(user.getBody());

        //TODO Check if data is actually valid
        if(userFromHttpBody.isPresent()){
            UserEntity newUser = userEntityMapper(userFromHttpBody.get());
            UserEntity returnedUser = userRepository.save(newUser);
            insertedUser = Optional.of(returnedUser);
        }

        return insertedUser;
    }

    public Optional<UserEntity> updateUser(Integer id, HttpEntity<String> user) {
        Optional<UserEntity> updatedUser = Optional.empty();
        Optional<UserEntity> oldUser = Optional.ofNullable(userRepository.getUserEntityById(id));

        if(oldUser.isEmpty()){
            return updatedUser;
        }

        Optional<User> userFromHttpBody = jsonToUserModel(user.getBody());

        //TODO Check if data is actually valid
        if(userFromHttpBody.isPresent()){
            UserEntity userToBeUpdated = updateUser(userFromHttpBody.get(), oldUser.get());
            UserEntity returnedUser = userRepository.save(userToBeUpdated);
            updatedUser = Optional.of(returnedUser);
        }
        return updatedUser;
    }

    private UserEntity updateUser(User newUserInformation, UserEntity user){
        if(newUserInformation.getName() != null){
            user.setName(newUserInformation.getName());
        }

        if(newUserInformation.getEmail() != null){
            user.setEmail(newUserInformation.getEmail());
        }

        if(newUserInformation.getStatus() != null){
            user.setStatus(newUserInformation.getStatus());
        }

        return user;
    }

    private UserEntity userEntityMapper(User user){
        return new UserEntity(user.getName(),user.getEmail(), user.getStatus());
    }

    private Optional<User> jsonToUserModel(String jsonUser){
        ObjectMapper mapper = new ObjectMapper();
        Optional<User> user = Optional.empty();
        try {
            User mappedUser = mapper.readValue(jsonUser, User.class);
            user = Optional.of(mappedUser);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Optional<UserEntity> deleteUser(Integer id) {

        Optional<UserEntity> userToBeDeleted = userRepository.findById(id);

        if(userToBeDeleted.isPresent()){
            userRepository.deleteById(id);
        }

        return userToBeDeleted;
    }
}
