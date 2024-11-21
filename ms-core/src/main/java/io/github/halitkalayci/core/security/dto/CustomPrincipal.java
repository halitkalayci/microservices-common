package io.github.halitkalayci.core.security.dto;

import java.util.UUID;

public class CustomPrincipal {
  private UUID userId;
  private String username;

  public CustomPrincipal(UUID userId, String username) {
    this.userId = userId;
    this.username = username;
  }

  public UUID getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }
}