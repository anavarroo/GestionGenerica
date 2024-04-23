package com.api.gestiongenerica.services;


import com.api.gestiongenerica.persistence.dto.UserDto;

public interface UserServiceI {

    /**
     * Actualiza la descripción de un usuario.
     *
     * @param correo Correo del usuario a actualizar.
     * @param userDto DTO del usuario con la nueva información.
     * @return DTO del usuario actualizado.
     */
    UserDto actualizarUsuario(String correo, UserDto userDto);

}
