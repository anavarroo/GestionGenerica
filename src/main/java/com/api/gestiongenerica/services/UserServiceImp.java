package com.api.gestiongenerica.services;

import com.api.gestiongenerica.persistence.dto.UserDto;
import com.api.gestiongenerica.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.gestiongenerica.persistence.model.User;
import com.api.gestiongenerica.persistence.repository.UserRepositoryI;

@Service
public class UserServiceImp implements UserServiceI {

<<<<<<< HEAD
    private final UserRepositoryI userRepository;

    @Autowired
    public UserServiceImp(UserRepositoryI userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User crearUsuario(User user) {
        return userRepository.save(user);
=======
    private final UserRepositoryI userRepositoryI;

    @Autowired
    public UserServiceImp(UserRepositoryI userRepositoryI) {
        this.userRepositoryI = userRepositoryI;
    }

    @Override
    public UserDto consultarUsuario(String correo) {
        User user = userRepositoryI.findByCorreo(correo);

        return convertToDto(user);
    }

    @Override
    public UserDto actualizarUsuario(String correo, UserDto userDto) {
        User user = userRepositoryI.findByCorreo(correo);

        user.setNombre(userDto.getNombre());
        user.setApellidos(userDto.getApellidos());
        user.setCorreo(userDto.getCorreo());
        user.setEdad(userDto.getEdad());
        user.setDireccion(userDto.getDireccion());
        user.setTelefono(userDto.getTelefono());

        User usuarioActualizado = userRepositoryI.save(user);

        return convertToDto(usuarioActualizado);
    }

    /**
     * Convierte un objeto User en un objeto UserDto.
     *
     * @param user Objeto User a convertir.
     * @return Objeto UserDto convertido.
     */
    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setNombre(user.getNombre());
        userDto.setApellidos(user.getApellidos());
        userDto.setCorreo(user.getCorreo());
        userDto.setEdad(user.getEdad());
        userDto.setDireccion(user.getDireccion());
        userDto.setTelefono(user.getTelefono());
        return userDto;
<<<<<<< HEAD
>>>>>>> main
=======
>>>>>>> alejandro
>>>>>>> 8df3f74459fda19c1fd42a34fc9d3d46aa3ba138
    }
}
