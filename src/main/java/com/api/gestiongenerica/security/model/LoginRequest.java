package com.api.gestiongenerica.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    /** El nombre de usuario proporcionado en la solicitud de inicio de sesión. */
    private String correo;

    /** La contraseña proporcionada en la solicitud de inicio de sesión. */
    private String contrasena;
}
