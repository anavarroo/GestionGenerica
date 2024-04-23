package com.api.gestiongenerica.services;

import com.api.gestiongenerica.persistence.model.User;

import com.api.gestiongenerica.persistence.dto.UserDto;

import com.api.gestiongenerica.persistence.dto.UserDto;

public interface UserServiceI {

    /**
<<<<<<< HEAD
=======
     * Muestra un usuario segun su correo.
     *
     * @param correo Correo del usuario que se busca.
     * @return DTO del usuario encontrado.
     */
    UserDto consultarUsuario(String correo);

    /**
>>>>>>> alejandro
     * Actualiza la descripción de un usuario.
     *
     * @param correo Correo del usuario a actualizar.
     * @param userDto DTO del usuario con la nueva información.
     * @return DTO del usuario actualizado.
     */
    UserDto actualizarUsuario(String correo, UserDto userDto);


    User crearUsuario(User user);
}
