package com.api.gestiongenerica.controller;
import com.api.gestiongenerica.controllers.UserController;
import com.api.gestiongenerica.persistence.dto.UserDto;
import com.api.gestiongenerica.persistence.model.User;
import com.api.gestiongenerica.services.UserServiceI;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @Test
    void crearUsuario_CreaNuevoUsuario_RespuestaConNuevoUsuarioY201Created() {
        // Arrange
        UserServiceI mockUserService = mock(UserServiceI.class);
        User user = new User();
        when(mockUserService.crearUsuario(any(User.class))).thenReturn(user);
        UserController userController = new UserController(mockUserService);

        // Act
        ResponseEntity<User> response = userController.crearUsuario(user);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());
    }

    @Test
    void consultarUsuario_ConsultaUsuarioPorCorreo_RespuestaConUserDto() {
        // Arrange
        UserServiceI mockUserService = mock(UserServiceI.class);
        UserDto userDto = new UserDto();
        when(mockUserService.consultarUsuario(anyString())).thenReturn(userDto);
        UserController userController = new UserController(mockUserService);

        // Act
        ResponseEntity<UserDto> response = userController.consultarUsuario("correo");

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDto, response.getBody());
    }


    @Test
    void borrarUsuarioPorEmail_EliminaUsuarioPorCorreo() {
        // Arrange
        UserServiceI mockUserService = mock(UserServiceI.class);
        UserController userController = new UserController(mockUserService);

        // Act
        userController.borrarUsuarioPorEmail("correo");

        // Assert
        verify(mockUserService, times(1)).borrarUsuarioPorEmail("correo");
    }
}
