package com.api.gestiongenerica.controllers;

<<<<<<< HEAD
import com.api.gestiongenerica.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.gestiongenerica.services.UserServiceI;

=======
import com.api.gestiongenerica.persistence.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.gestiongenerica.services.UserServiceI;
>>>>>>> main

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin
public class UserController {

<<<<<<< HEAD
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


=======
    private final UserServiceI userServiceI;

    @Autowired
    public UserController(UserServiceI userServiceI) {
        this.userServiceI = userServiceI;
    }

    /**
     * Edita los datos de un usuario.
     *
     * @param correo Objeto UserDto con la nueva descripción del usuario.
     * @return ResponseEntity con el objeto UserDto actualizado.
     */
    @GetMapping("/{correo}")
    public ResponseEntity<UserDto> consultarUsuario(
            @PathVariable String correo) {
        UserDto userDto = userServiceI.consultarUsuario(correo);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Edita los datos de un usuario.
     *
     * @param correo Objeto UserDto con la nueva descripción del usuario.
     * @param userDto Objeto Authentication para obtener el nombre de usuario del usuario autenticado.
     * @return ResponseEntity con el objeto UserDto actualizado.
     */
    @PutMapping("/editar/{correo}")
    public ResponseEntity<UserDto> actualizarUsuario(
            @PathVariable String correo,
            @RequestBody UserDto userDto) {
        UserDto usuarioActualizado = userServiceI.actualizarUsuario(correo, userDto);
        return ResponseEntity.ok(usuarioActualizado);
    }
>>>>>>> main

}
