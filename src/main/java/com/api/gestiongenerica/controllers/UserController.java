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
    @PostMapping("/crear")
    public ResponseEntity<User> crearUsuario(@RequestBody User user) {
        User nuevoUsuario = userServiceI.crearUsuario(user);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    /**
     * Edita los datos de un usuario.
     *
     * @param userDto Objeto UserDto con la nueva información del usuario.
     * @param correo Objeto Authentication para obtener el correo
     *                       del usuario autenticado.
     * @return ResponseEntity con el objeto UserDto actualizado.
     */
    @PutMapping("/editar/{correo}")
    public ResponseEntity<UserDto> actualizarUsuario(
            @RequestBody UserDto userDto,
            @PathVariable String correo) {
        UserDto usuarioActualizado = userServiceI.actualizarUsuario(correo, userDto);
        return ResponseEntity.ok(usuarioActualizado);
    }


    /**
     * Elimina un usuario de la base de datos utilizando su direccion de correo electrónico como identificador único.
     *
     * @param correo La dirección de correo electrónico del usuario que se va a eliminar.
     */
    @DeleteMapping("/borrar/{correo}")
    public void borrarUsuarioPorEmail(@PathVariable String correo) {
        userServiceI.borrarUsuarioPorEmail(correo);
    }

    /**
     * Consultar un usuario por su correo.
     *
     * @param nombre Nombre del usuario.
     * @return ResponseEntity con el objeto UserDto.
     */
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<UserDto> consultarUsuarioPorNombre(
            @PathVariable String nombre) {
        UserDto userDto = userServiceI.consultarUsuarioPorNombre(nombre);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Consultar un usuario por su correo.
     *
     * @param apellidos Apellidos del usuario.
     * @return ResponseEntity con el objeto UserDto.
     */
    @GetMapping("/apellidos/{apellidos}")
    public ResponseEntity<UserDto> consultarUsuarioPorApellidos(
            @PathVariable String apellidos) {
        UserDto userDto = userServiceI.consultarUsuarioPorApellidos(apellidos);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Consultar un usuario por su correo.
     *
     * @param edad Edad del usuario.
     * @return ResponseEntity con el objeto UserDto.
     */
    @GetMapping("/edad/{edad}")
    public ResponseEntity<UserDto> consultarUsuarioPorEdad(
            @PathVariable int edad) {
        UserDto userDto = userServiceI.consultarUsuarioPorEdad(edad);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Consultar un usuario por su correo.
     *
     * @param correo Correo del usuario.
     * @return ResponseEntity con el objeto UserDto.
     */
    @GetMapping("/correo/{correo}")
    public ResponseEntity<UserDto> consultarUsuarioPorCorreo(
            @PathVariable String correo) {
        UserDto userDto = userServiceI.consultarUsuarioPorCorreo(correo);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Consultar un usuario por su correo.
     *
     * @param direccion Direccion del usuario.
     * @return ResponseEntity con el objeto UserDto.
     */
    @GetMapping("/direccion/{direccion}")
    public ResponseEntity<UserDto> consultarUsuarioPorDireccion(
            @PathVariable String direccion) {
        UserDto userDto = userServiceI.consultarUsuarioPorDireccion(direccion);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Consultar un usuario por su correo.
     *
     * @param telefono Telefono del usuario.
     * @return ResponseEntity con el objeto UserDto.
     */
    @GetMapping("/telefono/{telefono}")
    public ResponseEntity<UserDto> consultarUsuarioPorTelefono(
            @PathVariable int telefono) {
        UserDto userDto = userServiceI.consultarUsuarioPorTelefono(telefono);
        return ResponseEntity.ok(userDto);
    }
}
