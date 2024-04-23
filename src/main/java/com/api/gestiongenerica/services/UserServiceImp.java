package com.api.gestiongenerica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.gestiongenerica.persistence.model.User;
import com.api.gestiongenerica.persistence.repository.UserRepositoryI;

@Service
public class UserServiceImp implements UserServiceI {

    private final UserRepositoryI userRepository;

    @Autowired
    public UserServiceImp(UserRepositoryI userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User crearUsuario(User user) {
        return userRepository.save(user);
    }
}
