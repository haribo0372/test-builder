package com.mzfk.test.builder.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mzfk.test.builder.dto.auth.JwtAuthenticationResponse;
import com.mzfk.test.builder.dto.auth.SignInRequest;
import com.mzfk.test.builder.dto.auth.SignUpRequest;
import com.mzfk.test.builder.service.AuthenticationService;


@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        log.info("POST /sign-up with request body {username={}, email={}} started", request.getUsername(), request.getEmail());
        JwtAuthenticationResponse response = authenticationService.signUp(request);
        log.info("POST /sign-up with request body {username={}, email={}} ended", request.getUsername(), request.getEmail());
        return response;
    }

    @Operation(summary = "Авторизация пользователя")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        log.info("POST /sign-in with request body {username={}} started", request.getUsername());
        JwtAuthenticationResponse response = authenticationService.signIn(request);
        log.info("POST /sign-in with request body {username={}} ended", request.getUsername());
        return response;
    }
}
