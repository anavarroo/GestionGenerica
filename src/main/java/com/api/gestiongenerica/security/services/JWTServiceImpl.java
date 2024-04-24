package com.api.gestiongenerica.security.services;

import com.api.gestiongenerica.persistence.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTServiceI{

    @Value("${jwt.secret}")
    String secretKey;

    /**
     * Genera un token JWT para el usuario dado.
     *
     * @param user Usuario para el cual se genera el token.
     * @return Token JWT generado.
     */
    @Override
    public String getToken(User user) {
        return getToken(Map.of("id", user.getId(),
                "correo", user.getCorreo()), user);
    }

    /**
     * Genera un token JWT para el usuario dado con los reclamos adicionales especificados.
     *
     * @param extraClaims Reclamos adicionales para incluir en el token.
     * @param user Usuario para el cual se genera el token.
     * @return Token JWT generado.
     */
    @Override
    public String getToken(Map<String, Object> extraClaims, User user) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getCorreo())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey())
                .compact();
    }

    /**
     * Obtiene la clave secreta utilizada para firmar los tokens JWT.
     *
     * @return Clave secreta.
     */
    @Override
    public SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Obtiene el nombre de usuario del token JWT.
     *
     * @param token Token JWT del cual obtener el nombre de usuario.
     * @return Nombre de usuario extraído del token.
     */
    @Override
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Verifica si un token JWT dado es válido para un usuario específico.
     *
     * @param token Token JWT a validar.
     * @param userDetails Detalles del usuario contra los cuales validar el token.
     * @return true si el token es válido para el usuario dado, false de lo contrario.
     */
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String correo = getUsernameFromToken(token);
        return (correo.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Metodos privados

    /**
     * Obtiene todos los reclamos del token JWT.
     *
     * @param token Token JWT del cual obtener los reclamos.
     * @return Todos los reclamos del token JWT.
     */
    private Claims getAllClaims(String token)
    {
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Obtiene un reclamo específico del token JWT.
     *
     * @param token Token JWT del cual obtener el reclamo.
     * @param claimsResolver Función para resolver el reclamo.
     * @param <T> Tipo de dato del reclamo.
     * @return El reclamo del token JWT.
     */
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Obtiene la fecha de expiración del token JWT.
     *
     * @param token Token JWT del cual obtener la fecha de expiración.
     * @return Fecha de expiración del token JWT.
     */
    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Verifica si el token JWT ha expirado.
     *
     * @param token Token JWT a verificar.
     * @return true si el token ha expirado, false de lo contrario.
     */
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

}
