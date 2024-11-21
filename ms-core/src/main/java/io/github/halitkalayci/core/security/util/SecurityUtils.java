package io.github.halitkalayci.core.security.util;

import io.github.halitkalayci.core.security.dto.CustomPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityUtils {
  public static UUID getLoggedInUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();

      if (principal instanceof CustomPrincipal) {
        CustomPrincipal userDetails = (CustomPrincipal) principal;
        return userDetails.getUserId();
      } else if (principal instanceof UUID) {
        return (UUID) principal;
      }
    }
    return null;
  }
}
