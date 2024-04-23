package com.api.gestiongenerica.controllers;

import com.api.gestiongenerica.persistence.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.gestiongenerica.services.UserServiceI;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin
public class UserController {

    private final UserServiceI userServiceI;

    @Autowired
    public UserController(UserServiceI userServiceI) {
        this.userServiceI = userServiceI;
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

}
