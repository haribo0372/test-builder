package com.mzfk.test.builder.service;

import com.mzfk.test.builder.dto.auth.JwtAuthenticationResponse;
import com.mzfk.test.builder.dto.auth.SignInRequest;
import com.mzfk.test.builder.dto.auth.SignUpRequest;
import com.mzfk.test.builder.model.Role;
import com.mzfk.test.builder.model.User;
import com.mzfk.test.builder.service.base.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserServiceImpl userService;
    private final JwtServiceImpl jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = new User(null,
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getEmail(),
                Role.ROLE_USER,
                null);

        userService.create(user);
        var jwt = jwtService.generateToken(user);
        log.info("Пользователь с username='{}' успешно зарегистрирован", request.getUsername());
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        log.info("Пользователь с username='{}' успешно вошел", request.getUsername());
        return new JwtAuthenticationResponse(jwt);
    }
}
