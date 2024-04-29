package com.api.gestiongenerica.services;

import com.api.gestiongenerica.persistence.model.User;

import com.api.gestiongenerica.persistence.dto.UserDto;

public interface UserServiceI {

    /**
     * Muestra un usuario segun su correo.
     *
     * @param nombre Nombre del usuario que se busca.
     * @return DTO del usuario encontrado.
     */
    UserDto consultarUsuarioPorNombre(String nombre);

    /**
     * Muestra un usuario segun su correo.
     *
     * @param apellidos Apellidos del usuario que se busca.
     * @return DTO del usuario encontrado.
     */
    UserDto consultarUsuarioPorApellidos(String apellidos);

    /**
     * Muestra un usuario segun su correo.
     *
     * @param edad Edad del usuario que se busca.
     * @return DTO del usuario encontrado.
     */
    UserDto consultarUsuarioPorEdad(int edad);

    /**
     * Muestra un usuario segun su correo.
     *
     * @param correo Correo del usuario que se busca.
     * @return DTO del usuario encontrado.
     */
    UserDto consultarUsuarioPorCorreo(String correo);

    /**
     * Muestra un usuario segun su correo.
     *
     * @param direccion Direccion del usuario que se busca.
     * @return DTO del usuario encontrado.
     */
    UserDto consultarUsuarioPorDireccion(String direccion);

    /**
     * Muestra un usuario segun su correo.
     *
     * @param telefono Telefono del usuario que se busca.
     * @return DTO del usuario encontrado.
     */
    UserDto consultarUsuarioPorTelefono(int telefono);

    /**
     * Actualiza la descripción de un usuario.
     *
     * @param correo Correo del usuario a actualizar.
     * @param userDto DTO del usuario con la nueva información.
     * @return DTO del usuario actualizado.
     */
    UserDto actualizarUsuario(String correo, UserDto userDto);

    /**
     * Crea un nuevo usuario en el sistema, encriptando la contraseña antes de guardarla en la base de datos.
     *
     * @param user El objeto User que representa al nuevo usuario a crear.
     * @return El objeto User creado y guardado en la base de datos.
     */
    User crearUsuario(User user);

    /**
     * Elimina un usuario de la base de datos por su dirección de correo electrónico.
     *
     * @param email La dirección de correo electrónico del usuario a eliminar.
     */
    void borrarUsuarioPorEmail(String email);
}
