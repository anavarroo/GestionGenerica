package com.api.gestiongenerica.security;

import com.api.gestiongenerica.security.model.AuthResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthResponseTest {

    @Test
    void constructor_CreaInstanciaConValoresIniciales() {
        // Arrange
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        // Act
        AuthResponse response = new AuthResponse(token);

        // Assert
        assertNotNull(response);
        assertEquals(token, response.getToken());
    }

    @Test
    void settersYgetters_ModificaYObtieneValoresCorrectamente() {
        // Arrange
        AuthResponse response = new AuthResponse();

        // Act
        response.setToken("newAuthToken");

        // Assert
        assertEquals("newAuthToken", response.getToken());
    }
}
