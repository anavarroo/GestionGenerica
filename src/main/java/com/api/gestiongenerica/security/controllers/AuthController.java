package com.api.gestiongenerica.security.controllers;

import com.api.gestiongenerica.security.model.AuthResponse;
import com.api.gestiongenerica.security.model.LoginRequest;
import com.api.gestiongenerica.security.model.RegisterRequest;
import com.api.gestiongenerica.security.services.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@Order(1)
public class AuthController {

    private final AuthServiceImpl authMngm;

    @Autowired
    public AuthController(AuthServiceImpl authMngm) {
        this.authMngm = authMngm;
    }

    /**
     * Endpoint para el registro de usuarios.
     *
     * @param request Datos de registro del usuario.
     * @return Respuesta con el token de autenticación.
     */
    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authMngm.register(request));
    }

    /**
     * Endpoint para la autenticación de usuarios.
     *
     * @param request Datos de inicio de sesión del usuario.
     * @return Respuesta con el token de autenticación.
     */
    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authMngm.login(request));
    }
}
