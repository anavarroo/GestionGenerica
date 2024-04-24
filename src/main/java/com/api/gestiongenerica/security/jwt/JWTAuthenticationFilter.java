package com.api.gestiongenerica.security.jwt;

import com.api.gestiongenerica.security.services.JWTServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

    private final JWTServiceImpl jwtMngm;

    private final UserDetailsService userDetailsService;

    @Autowired
    public JWTAuthenticationFilter(JWTServiceImpl jwtMngm, UserDetailsService userDetailsService) {
        this.jwtMngm = jwtMngm;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Método para realizar la autenticación basada en JWT.
     * Extrae el token del encabezado de autorización, valida el token y establece la autenticación en el
     * contexto de seguridad.
     *
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @param filterChain El filtro de cadena.
     * @throws ServletException Si ocurre una excepción de servlet.
     * @throws IOException Si ocurre una excepción de entrada/salida.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String token = getTokenFromRequest(request);
        final String username;

        if (token == null) {

            filterChain.doFilter(request, response);
            return;
        }

        username = jwtMngm.getUsernameFromToken(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =userDetailsService.loadUserByUsername(username);

            if (jwtMngm.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Método para extraer el token de la solicitud HTTP.
     *
     * @param request La solicitud HTTP.
     * @return El token JWT.
     */
    private String getTokenFromRequest(HttpServletRequest request){

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }
}
