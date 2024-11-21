package com.etiya.identityservice.controller;

import io.github.halitkalayci.dto.auth.request.RegisterRequest;
import io.github.halitkalayci.dto.auth.response.RegisterResponse;
import com.etiya.identityservice.dto.LoginRequest;
import com.etiya.identityservice.service.auth.AuthService;
import io.github.halitkalayci.core.security.dto.AccessToken;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class AuthController {
  private final AuthService authService;
  private final MessageSource messageSource;

  @PostMapping("login")
  public ResponseEntity<AccessToken> login(@Valid @RequestBody LoginRequest loginDto){
    return ResponseEntity.ok(authService.login(loginDto));
  }


  @PostMapping("register")
  public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerDto){
    return ResponseEntity.ok(authService.register(registerDto));
  }
}