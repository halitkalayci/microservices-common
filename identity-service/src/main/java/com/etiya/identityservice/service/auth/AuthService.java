package com.etiya.identityservice.service.auth;

import io.github.halitkalayci.core.security.dto.AccessToken;
import io.github.halitkalayci.dto.auth.request.RegisterRequest;
import io.github.halitkalayci.dto.auth.response.RegisterResponse;
import com.etiya.identityservice.dto.LoginRequest;

public interface AuthService {
  AccessToken login(LoginRequest loginDto);
  RegisterResponse register(RegisterRequest registerDto);
}
