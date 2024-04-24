package com.api.gestiongenerica.security.services;

import com.api.gestiongenerica.persistence.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Map;

public interface JWTServiceI {

    /**
     * Genera un token JWT para el usuario dado.
     *
     * @param user El usuario para el cual se genera el token.
     * @return El token JWT generado.
     */
    String getToken(User user);

    /**
     * Genera un token JWT para el usuario dado con reclamaciones adicionales.
     *
     * @param extraClaims Reclamaciones adicionales a incluir en el token.
     * @param user El usuario para el cual se genera el token.
     * @return El token JWT generado.
     */
    String getToken(Map<String, Object> extraClaims, User user);

    /**
     * Obtiene la clave utilizada para firmar y verificar tokens JWT.
     *
     * @return La clave utilizada para JWT.
     */
    Key getKey();

    /**
     * Extrae el nombre de usuario del token JWT.
     *
     * @param token El token JWT del que se extrae el nombre de usuario.
     * @return El nombre de usuario extraído del token.
     */
    String getUsernameFromToken(String token);

    /**
     * Verifica si un token JWT dado es válido para un UserDetails específico.
     *
     * @param token El token JWT a verificar.
     * @param userDetails El UserDetails para el cual se verifica la validez del token.
     * @return true si el token es válido para el UserDetails dado, false de lo contrario.
     */
    boolean isTokenValid(String token, UserDetails userDetails);
}
