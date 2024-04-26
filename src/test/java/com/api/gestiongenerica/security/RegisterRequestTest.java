package com.api.gestiongenerica.security;

import com.api.gestiongenerica.persistence.model.Role;
import com.api.gestiongenerica.security.model.RegisterRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegisterRequestTest {

    /*
    @Test
    void constructor_CreaInstanciaConValoresIniciales() {
        // Arrange
        String nombre = "John";
        String apellidos = "Doe";
        int edad = 30;
        String correo = "john.doe@example.com";
        String direccion = "123 Main St";
        int telefono = 123456789;
        String contrasena = "password";

        // Act
        RegisterRequest request = new RegisterRequest(nombre, apellidos, edad, correo, direccion, telefono, contrasena, Role.USER);

        // Assert
        assertNotNull(request);
        assertEquals(nombre, request.getNombre());
        assertEquals(apellidos, request.getApellidos());
        assertEquals(edad, request.getEdad());
        assertEquals(correo, request.getCorreo());
        assertEquals(direccion, request.getDireccion());
        assertEquals(telefono, request.getTelefono());
        assertEquals(contrasena, request.getContrasena());
    }


     */
    @Test
    void settersYgetters_ModificaYObtieneValoresCorrectamente() {
        // Arrange
        RegisterRequest request = new RegisterRequest();

        // Act
        request.setNombre("Jane");
        request.setApellidos("Doe");
        request.setEdad(25);
        request.setCorreo("jane.doe@example.com");
        request.setDireccion("456 Elm St");
        request.setTelefono(987654321);
        request.setContrasena("password123");

        // Assert
        assertEquals("Jane", request.getNombre());
        assertEquals("Doe", request.getApellidos());
        assertEquals(25, request.getEdad());
        assertEquals("jane.doe@example.com", request.getCorreo());
        assertEquals("456 Elm St", request.getDireccion());
        assertEquals(987654321, request.getTelefono());
        assertEquals("password123", request.getContrasena());
    }
}
