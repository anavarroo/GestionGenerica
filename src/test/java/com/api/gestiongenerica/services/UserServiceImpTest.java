package com.api.gestiongenerica.services;

import com.api.gestiongenerica.persistence.dto.UserDto;
import com.api.gestiongenerica.persistence.model.User;
import com.api.gestiongenerica.persistence.repository.UserRepositoryI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

// import static com.api.gestiongenerica.persistence.model.Role.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImpTest {

    @Mock
    private UserRepositoryI userRepository;

    @InjectMocks
    private UserServiceImp userService;

    @Captor
    private ArgumentCaptor<User> userCaptor;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCrearUsuario() {
        User user = new User();
        user.setContrasena("password");
        user.setCorreo("test@example.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.crearUsuario(user);

        assertNotNull(result.getContrasena()); // Verifica que la contraseña encriptada no sea nula
        assertTrue(new BCryptPasswordEncoder().matches("password", result.getContrasena())); // Verifica que la contraseña encriptada sea válida
        verify(userRepository, times(1)).save(user); // Verifica que el método save del repositorio se haya llamado una vez
    }
    @Test
    void testConsultarUsuario() {
        User user = new User();
        user.setCorreo("test@example.com");

        when(userRepository.findByCorreo("test@example.com")).thenReturn(user);

        UserDto result = userService.consultarUsuario("test@example.com");

        assertEquals(user.getCorreo(), result.getCorreo()); // Comprueba que los correos electrónicos coincidan
        verify(userRepository, times(1)).findByCorreo("test@example.com"); // Verifica que el método findByCorreo del repositorio se haya llamado una vez
    }

    @Test
    void testActualizarUsuario() {
        User user = new User();
        user.setCorreo("test@example.com");

        UserDto userDto = new UserDto();
        userDto.setCorreo("new@example.com");

        when(userRepository.findByCorreo("test@example.com")).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDto result = userService.actualizarUsuario("test@example.com", userDto);

        assertEquals(userDto.getCorreo(), result.getCorreo()); // Comprueba que el correo electrónico se haya actualizado correctamente
        verify(userRepository, times(1)).findByCorreo("test@example.com"); // Verifica que el método findByCorreo del repositorio se haya llamado una vez
        verify(userRepository, times(1)).save(any(User.class)); // Verifica que el método save del repositorio se haya llamado una vez
    }

    @Test
    void testBorrarUsuarioPorEmail() {
        User user = new User();
        user.setCorreo("test@example.com");

        when(userRepository.findByCorreo("test@example.com")).thenReturn(user);

        userService.borrarUsuarioPorEmail("test@example.com");

        verify(userRepository, times(1)).delete(user); // Verifica que el método delete del repositorio se haya llamado una vez
    }

    @Test
    void testBorrarUsuarioPorEmailUsuarioNoEncontrado() {
        when(userRepository.findByCorreo("test@example.com")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.borrarUsuarioPorEmail("test@example.com");
        });

        verify(userRepository, never()).delete(any(User.class)); // Verifica que el método delete del repositorio no se haya llamado
    }

    @Test
    void getUsername_ReturnsCorrectUsername() {
        // Arrange
        String correo = "usuario@example.com";
        User user = new User();
        user.setCorreo(correo);

        // Act
        String correo2 = user.getCorreo();

        // Assert
        assertEquals(correo, correo2);
    }


    @Test
    void getPassword_RetornaContraseña() {
        // Arrange
        String password = "testPassword";
        User user = new User();
        user.setContrasena(password);

        // Act
        String retrievedPassword = user.getPassword();

        // Assert
        assertEquals(password, retrievedPassword);
    }

    @Test
    void getUsername_RetornaNombreUsuario() {
        // Arrange
        String username = "testUsername";
        User user = new User();
        user.setCorreo(username);

        // Act
        String retrievedUsername = user.getUsername();

        // Assert
        assertEquals(username, retrievedUsername);
    }

    @Test
    void isAccountNonExpired_RetornaTrue() {
        // Arrange
        User user = new User();

        // Act
        boolean isAccountNonExpired = user.isAccountNonExpired();

        // Assert
        assertTrue(isAccountNonExpired);
    }

    @Test
    void isAccountNonLocked_RetornaTrue() {
        // Arrange
        User user = new User();

        // Act
        boolean isAccountNonLocked = user.isAccountNonLocked();

        // Assert
        assertTrue(isAccountNonLocked);
    }

    @Test
    void isCredentialsNonExpired_RetornaTrue() {
        // Arrange
        User user = new User();

        // Act
        boolean isCredentialsNonExpired = user.isCredentialsNonExpired();

        // Assert
        assertTrue(isCredentialsNonExpired);
    }

    @Test
    void isEnabled_RetornaTrue() {
        // Arrange
        User user = new User();

        // Act
        boolean isEnabled = user.isEnabled();

        // Assert
        assertTrue(isEnabled);
    }
}
