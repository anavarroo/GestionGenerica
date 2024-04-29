package com.api.gestiongenerica.security.services;

import com.api.gestiongenerica.persistence.model.User;
import com.api.gestiongenerica.persistence.repository.UserRepositoryI;
import com.api.gestiongenerica.security.model.AuthResponse;
import com.api.gestiongenerica.security.model.LoginRequest;
import com.api.gestiongenerica.security.model.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthServiceI {

    private final UserRepositoryI userRepo;

    private final JWTServiceI jwtMngm;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(UserRepositoryI userRepo, JWTServiceI jwtMngm, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.jwtMngm = jwtMngm;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param request La solicitud de registro.
     * @return La respuesta de autenticación.
     */
    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = new User(request.getNombre(),
                request.getApellidos(), request.getCorreo(),
                passwordEncoder.encode(request.getContrasena()));

        userRepo.save(user);

        return new AuthResponse(jwtMngm.getToken(user));
    }

    /**
     * Inicia sesión en el sistema.
     *
     * @param request La solicitud de inicio de sesión.
     * @return La respuesta de autenticación.
     */
    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getCorreo(),
                request.getContrasena()));
        User user = userRepo.findByCorreo(request.getCorreo());
        return new AuthResponse(jwtMngm.getToken(user));
    }

}
