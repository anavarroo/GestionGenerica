package com.api.gestiongenerica.security;

import com.api.gestiongenerica.security.model.LoginRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void constructor_CreaInstanciaConValoresIniciales() {
        // Arrange
        String correo = "john.doe@example.com";
        String contrasena = "password";

        // Act
        LoginRequest request = new LoginRequest(correo, contrasena);

        // Assert
        assertNotNull(request);
        assertEquals(correo, request.getCorreo());
        assertEquals(contrasena, request.getContrasena());
    }

    @Test
    void settersYgetters_ModificaYObtieneValoresCorrectamente() {
        // Arrange
        LoginRequest request = new LoginRequest();

        // Act
        request.setCorreo("jane.doe@example.com");
        request.setContrasena("password123");

        // Assert
        assertEquals("jane.doe@example.com", request.getCorreo());
        assertEquals("password123", request.getContrasena());
    }
}

