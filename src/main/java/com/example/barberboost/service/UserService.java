package com.example.barberboost.service;


import com.example.barberboost.models.UserModel;
import com.example.barberboost.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel save(UserModel userModel) {
        System.out.printf("chegou UserModel");
        return userRepository.save(userModel);
    }

    public UserDetails findByLogin(String login) {
        return userRepository.findByLogin(login);
    }


}
