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
        userService = new UserServiceImp(userRepository);

    }

    /*
    @Test
    void crearUsuario() {
        // Arrange
        User user = new User("Pepe", "pepon", 20, "pepepalotas@gmail.com","Vedruna", 646135607, "Perdigo", USER);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1); // Simula la asignación de un ID después de guardar en la base de datos
            return savedUser;
        });

        // Act
        User createdUser = userService.crearUsuario(user);

        // Assert
        assertEquals("Pepe", createdUser.getNombre());
        assertEquals("pepon", createdUser.getApellidos());
        assertEquals(20, createdUser.getEdad());
        assertEquals("pepepalotas@gmail.com", createdUser.getCorreo());
        assertEquals("Vedruna", createdUser.getDireccion());
        assertEquals(646135607, createdUser.getTelefono());
        assertEquals(USER, createdUser.getRole());


        // Verifica que el método save del repositorio se haya llamado con el usuario capturado
        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();

        // Verifica que la contraseña encriptada se haya guardado en el usuario
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertTrue(encoder.matches("Perdigo", savedUser.getContrasena()));
    }

    @Test
    void consultarUsuario_UserExists() {
        String correo = "pepepalotas@gmail.com";
        User user = new User("Pepe", "pepon", 20, correo, "Vedruna", 646135607, "Perdigo", USER);
        when(userRepository.findByCorreo(correo)).thenReturn(user);

        UserDto userDto = userService.consultarUsuario(correo);

        assertNotNull(userDto);
        assertEquals(user.getCorreo(), userDto.getCorreo());
    }

 /*
    @Test
    void consultarUsuario_UserNotFound() {
        String correo = "usuario_inexistente@gmail.com";
        when(userRepository.findByCorreo(correo)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userService.consultarUsuario(correo));
    }

  */



    /*
    @Test
    void actualizarUsuario() {
        String correo = "pepepalotas@gmail.com";
        UserDto userDto = new UserDto("Pepe", "pepon", 20, correo, "Vedruna", 646135607);
        User existingUser = new User("Old Name", "Old Last Name", 25, correo, "Old Address", 123456789, "Old Password", USER);
        when(userRepository.findByCorreo(correo)).thenReturn(existingUser);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserDto updatedUser = userService.actualizarUsuario(correo, userDto);

        assertNotNull(updatedUser);
        assertEquals(userDto.getCorreo(), updatedUser.getCorreo());
        assertEquals(userDto.getNombre(), updatedUser.getNombre());
        assertEquals(userDto.getApellidos(), updatedUser.getApellidos());
    }

    @Test
    void borrarUsuarioPorEmail_UserExists() {
        String correo = "pepepalotas@gmail.com";
        User existingUser = new User("Pepe", "pepon", 20, correo, "Vedruna", 646135607, "Perdigo", USER);
        when(userRepository.findByCorreo(correo)).thenReturn(existingUser);

        userService.borrarUsuarioPorEmail(correo);

        verify(userRepository, times(1)).delete(existingUser);
    }

    @Test
    void borrarUsuarioPorEmail_UserNotFound() {
        String correo = "usuario_inexistente@gmail.com";
        when(userRepository.findByCorreo(correo)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userService.borrarUsuarioPorEmail(correo));
    }

    @Test
    void consultarUsuario() {
        // Arrange
        String correo = "usuario@gmail.com";
        User user = new User("John", "Doe", 30, correo, "123 Main St", 1234567890, "password",USER);
        UserDto expectedUserDto = new UserDto("John", "Doe", 30, correo, "123 Main St", 1234567890);

        // Simular que el repositorio retorna el usuario cuando se busca por el correo
        when(userRepository.findByCorreo(correo)).thenReturn(user);

        // Act
        UserDto actualUserDto = userService.consultarUsuario(correo);

        // Assert
        assertEquals(expectedUserDto.getNombre(), actualUserDto.getNombre());
        assertEquals(expectedUserDto.getApellidos(), actualUserDto.getApellidos());
        assertEquals(expectedUserDto.getEdad(), actualUserDto.getEdad());
        assertEquals(expectedUserDto.getCorreo(), actualUserDto.getCorreo());
        assertEquals(expectedUserDto.getDireccion(), actualUserDto.getDireccion());
        assertEquals(expectedUserDto.getTelefono(), actualUserDto.getTelefono());
    }

     */
    @Test
    void testCrearUsuario() {
        User user = new User();
        user.setCorreo("test@example.com");
        user.setContrasena("password");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.crearUsuario(user);

        assertNotNull(createdUser);
        assertEquals("test@example.com", createdUser.getCorreo());
        // Comprueba que la contraseña se ha encriptado
        assertNotEquals("password", createdUser.getContrasena());
    }

    @Test
    void testConsultarUsuario() {
        String correo = "test@example.com";
        User user = new User();
        user.setCorreo(correo);

        when(userRepository.findByCorreo(correo)).thenReturn(user);

        UserDto userDto = userService.consultarUsuario(correo);

        assertNotNull(userDto);
        assertEquals(correo, userDto.getCorreo());
    }

    @Test
    void testActualizarUsuario() {
        String correo = "test@example.com";
        UserDto userDto = new UserDto();
        userDto.setNombre("Nombre Actualizado");
        userDto.setApellidos("Apellidos Actualizados");

        User user = new User();
        user.setCorreo(correo);

        when(userRepository.findByCorreo(correo)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDto updatedUserDto = userService.actualizarUsuario(correo, userDto);

        assertNotNull(updatedUserDto);
        assertEquals("Nombre Actualizado", updatedUserDto.getNombre());
        assertEquals("Apellidos Actualizados", updatedUserDto.getApellidos());
    }

    @Test
    void testBorrarUsuarioPorEmail() {
        String correo = "test@example.com";
        User user = new User();
        user.setCorreo(correo);

        when(userRepository.findByCorreo(correo)).thenReturn(user);

        assertDoesNotThrow(() -> userService.borrarUsuarioPorEmail(correo));
    }

    @Test
    void testBorrarUsuarioPorEmailUsuarioNoEncontrado() {
        String correo = "test@example.com";

        when(userRepository.findByCorreo(correo)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userService.borrarUsuarioPorEmail(correo));
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
