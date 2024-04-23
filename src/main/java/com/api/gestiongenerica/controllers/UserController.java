package com.api.gestiongenerica.controllers;

import com.api.gestiongenerica.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.gestiongenerica.services.UserServiceI;


@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin
public class UserController {

    private final UserServiceI userService;

    @Autowired
    public UserController(UserServiceI userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> crearUsuario(@RequestBody User user) {
        User nuevoUsuario = userService.crearUsuario(user);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }



}
