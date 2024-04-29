package com.api.gestiongenerica.model;

import com.api.gestiongenerica.persistence.model.Role;
import com.api.gestiongenerica.persistence.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    @Test
    void testGetAuthorities() {
        // Crear un conjunto de roles para el usuario
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1, "ROLE_ADMIN")); // Suponiendo que el constructor de Role toma un id y un nombre
        roles.add(new Role(2, "ROLE_USER"));

        // Crear un usuario con los roles
        User user = new User("John", "Doe", 30, "john@example.com", "123 Main St", 1234567890, "password", roles);

        // Obtener las autoridades del usuario
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // Verificar que se hayan generado las autoridades correctamente
        assertEquals(2, authorities.size()); // Verifica que haya dos roles asignados
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))); // Verifica que el rol "ROLE_ADMIN" esté presente
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))); // Verifica que el rol "ROLE_USER" esté presente
    }
}
