package com.api.gestiongenerica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.gestiongenerica.services.UserServiceI;
import com.api.gestiongenerica.services.UserServiceImp;

@RestController
@RequestMapping("/api/v1/Usuarios")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceI userServiceI;

}
