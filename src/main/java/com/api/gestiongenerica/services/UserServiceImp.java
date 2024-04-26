package com.api.gestiongenerica.services;

import com.api.gestiongenerica.persistence.dto.UserDto;
import com.api.gestiongenerica.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.gestiongenerica.persistence.repository.UserRepositoryI;

@Service
public class UserServiceImp implements UserServiceI{

    private final UserRepositoryI userRepositoryI;

    @Autowired
    public UserServiceImp(UserRepositoryI userRepositoryI) {
        this.userRepositoryI = userRepositoryI;
    }


    /**
     * Crea un nuevo usuario en el sistema, encriptando la contraseña antes de guardarla en la base de datos.
     *
     * @param user El objeto User que representa al nuevo usuario a crear.
     * @return El objeto User creado y guardado en la base de datos.
     */
    @Override
    public User crearUsuario(User user) {
        String contrasenaSinEncriptar = user.getContrasena();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String contrasenaEncriptada = encoder.encode(contrasenaSinEncriptar);

        user.setContrasena(contrasenaEncriptada);

         return userRepositoryI.save(user);
    }


    /**
     * Encuentra un usuario por su correo y devuelve su DTO.
     *
     * @param correo Correo del usuario a buscar.
     * @return DTO del usuario encontrado.
     */
    @Override
    public UserDto consultarUsuario(String correo) {
        User user = userRepositoryI.findByCorreo(correo);

        return convertToDto(user);
    }


    /**
     * Actualiza la informacion de un usuario y devuelve su DTO actualizado.
     *
     * @param correo Correo del usuario cuya informacion se actualizará.
     * @param userDto DTO del usuario con la nueva informacion.
     * @return DTO del usuario actualizado.
     */
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
     * Elimina un usuario de la base de datos utilizando su dirección de correo electrónico como identificador único.
     *
     * @param correo La dirección de correo electrónico del usuario que se va a eliminar.
     * @throws UsernameNotFoundException Si no se encuentra ningún usuario con la dirección de correo electrónico proporcionada.
     */
    @Override
    public void borrarUsuarioPorEmail(String correo) {
        User user = userRepositoryI.findByCorreo(correo);
        if (user != null) {
            userRepositoryI.delete(user);
        } else {
            throw new UsernameNotFoundException("No se encontró ningún usuario con el correo electrónico proporcionado: " + correo);
        }
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
    }
}
