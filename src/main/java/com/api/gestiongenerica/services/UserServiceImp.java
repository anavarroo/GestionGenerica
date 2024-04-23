package com.api.gestiongenerica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.gestiongenerica.persistence.repository.UserRepositoryI;

@Service
public class UserServiceImp implements UserServiceI{


    @Autowired
    private UserRepositoryI userRepositoryI;
    
}
