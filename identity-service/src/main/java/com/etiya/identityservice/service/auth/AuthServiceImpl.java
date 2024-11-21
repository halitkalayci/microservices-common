package com.etiya.identityservice.service.auth;

import io.github.halitkalayci.dto.auth.request.RegisterRequest;
import io.github.halitkalayci.dto.auth.response.RegisterResponse;
import com.etiya.identityservice.dto.LoginRequest;
import com.etiya.identityservice.entity.User;
import com.etiya.identityservice.service.user.UserService;
import io.github.halitkalayci.core.security.dto.AccessToken;
import io.github.halitkalayci.core.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService
{
  private final PasswordEncoder passwordEncoder;
  private final UserService userService;
  private final JwtService jwtService;

  @Override
  public AccessToken login(LoginRequest loginDto) {
    User user = userService.loadUserByEmail(loginDto.getEmail());
    if(user==null)
      throw new UsernameNotFoundException(loginDto.getEmail());

    boolean passwordMatching = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
    if(!passwordMatching)
      throw new UsernameNotFoundException(loginDto.getEmail());

    return jwtService.generateToken(user.getUsername(), user.getId());
  }

  @Override
  public RegisterResponse register(RegisterRequest registerDto) {
    User userToAdd =new User();
    userToAdd.setEmail(registerDto.getEmail());
    userToAdd.setPassword(passwordEncoder.encode(registerDto.getPassword()));
    User user = userService.create(userToAdd);
    return new RegisterResponse(user.getId(), user.getUsername());
  }
}