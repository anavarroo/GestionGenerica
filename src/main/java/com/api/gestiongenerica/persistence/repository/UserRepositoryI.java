package com.api.gestiongenerica.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.gestiongenerica.persistence.model.User;

@Repository
public interface UserRepositoryI extends JpaRepository<User, Long>{

    /**
     * Busca un usuario por su correo.
     *
     * @param correo Correo de usuario a buscar.
     * @return Usuario encontrado.
     */
    User findByNombre(String correo);

    User findByApellidos(String correo);

    User findByEdad(int edad);

    User findByCorreo(String correo);

    User findByDireccion(String correo);

    User findByTelefono(int telefono);
}
