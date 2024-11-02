package io.github.halitkalayci.core.security.filter;

import io.github.halitkalayci.core.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtService jwtService;


  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain)
          throws ServletException, IOException {
    String jwtHeader = request.getHeader("Authorization");

    if(jwtHeader != null && jwtHeader.startsWith("Bearer "))
    {
      String jwt = jwtHeader.substring(7);
      String username = jwtService.extractUser(jwt);

      if(username!=null)
      {

        if(jwtService.validateToken(jwt))
        {
          List<String> roles = jwtService.getClaims(jwt).get("roles", List.class);
          List<SimpleGrantedAuthority> authorities = new ArrayList<>();
          if(roles!= null)
          {
            authorities = roles
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();
          }

          UsernamePasswordAuthenticationToken authenticationToken =
                  new UsernamePasswordAuthenticationToken(username, null, authorities);
          authenticationToken
                  .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
      }
    }
    filterChain.doFilter(request,response);
  }
}

