package com.mzfk.test.builder.service.base.auth;

import com.mzfk.test.builder.dto.auth.JwtAuthenticationResponse;
import com.mzfk.test.builder.dto.auth.SignInRequest;
import com.mzfk.test.builder.dto.auth.SignUpRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
