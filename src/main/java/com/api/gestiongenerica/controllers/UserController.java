package com.api.gestiongenerica.controllers;

import com.api.gestiongenerica.persistence.dto.UserDto;
import com.api.gestiongenerica.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
     * Crea un nuevo usuario en el sistema utilizando los datos proporcionados en el cuerpo de la solicitud.
     *
     * @param user El objeto User que contiene los datos del nuevo usuario a crear.
     * @return Un objeto ResponseEntity que contiene el nuevo usuario creado y el estado HTTP 201 Created.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<User> crearUsuario(@RequestBody User user) {
        User nuevoUsuario = userServiceI.crearUsuario(user);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }


    /**
     * Consultar un usuario por su correo.
     *
     * @param correo Correo del usuario.
     * @return ResponseEntity con el objeto UserDto.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/user/{correo}")
    public ResponseEntity<UserDto> consultarUsuario(
            @PathVariable String correo) {
        UserDto userDto = userServiceI.consultarUsuario(correo);
        return ResponseEntity.ok(userDto);
    }


    /**
     * Edita los datos de un usuario.
     *
     * @param userDto Objeto UserDto con la nueva información del usuario.
     * @param authentication Objeto Authentication para obtener el correo
     *                       del usuario autenticado.
     * @return ResponseEntity con el objeto UserDto actualizado.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/editar")
    public ResponseEntity<UserDto> actualizarUsuario(
            @RequestBody UserDto userDto,
            Authentication authentication) {
        String correo = authentication.getName();
        UserDto usuarioActualizado = userServiceI.actualizarUsuario(correo, userDto);
        return ResponseEntity.ok(usuarioActualizado);
    }
<<<<<<< HEAD
    /**
     * Controlador REST que maneja la solicitud para eliminar un usuario por su dirección de correo electrónico.
     *
     * @param correo La dirección de correo electrónico del usuario a eliminar.
=======


    /**
     * Elimina un usuario de la base de datos utilizando su direccion de correo electrónico como identificador único.
     *
     * @param correo La dirección de correo electrónico del usuario que se va a eliminar.
>>>>>>> features
     */
    @DeleteMapping("/borrar/{correo}")
    public void borrarUsuarioPorEmail(@PathVariable String correo) {
        userServiceI.borrarUsuarioPorEmail(correo);
    }
}
