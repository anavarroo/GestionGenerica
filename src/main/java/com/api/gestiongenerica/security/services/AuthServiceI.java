package com.api.gestiongenerica.security.services;

import com.api.gestiongenerica.security.model.AuthResponse;
import com.api.gestiongenerica.security.model.LoginRequest;
import com.api.gestiongenerica.security.model.RegisterRequest;

public interface AuthServiceI {

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param request La solicitud de registro.
     * @return La respuesta de autenticación.
     */
    AuthResponse register(RegisterRequest request);

    /**
     * Inicia sesión en el sistema.
     *
     * @param request La solicitud de inicio de sesión.
     * @return La respuesta de autenticación.
     */
    AuthResponse login(LoginRequest request);

}
