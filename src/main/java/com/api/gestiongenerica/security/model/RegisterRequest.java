package com.api.gestiongenerica.security.model;

import com.api.gestiongenerica.persistence.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    /** El nombre proporcionado en la solicitud de registro **/
    private String nombre;

    /** Los apellidos proporcionados en la solicitud de registro **/
    private String apellidos;

    /** El correo electrónico proporcionado en la solicitud de registro. */
    private String correo;


    /** La contraseña proporcionada en la solicitud de registro. */
    private String contrasena;

}
