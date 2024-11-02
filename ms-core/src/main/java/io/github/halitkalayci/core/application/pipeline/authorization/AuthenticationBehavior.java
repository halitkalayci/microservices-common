package io.github.halitkalayci.core.application.pipeline.authorization;

import an.awesome.pipelinr.Command;
import io.github.halitkalayci.core.exception.types.NotAuthenticatedException;
import io.github.halitkalayci.core.exception.types.NotAuthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationBehavior implements Command.Middleware {
  private final HttpServletRequest request;
  @Override
  public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
    if (command instanceof AuthenticatedRequest) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication == null || !authentication.isAuthenticated()) {
        throw new NotAuthenticatedException();
      }

      if(command instanceof AuthorizedRequest)
      {
        List<String> requiredRoles = ((AuthorizedRequest) command).getRequiredRoles();

        boolean hasAnyMatch = authentication
                .getAuthorities()
                .stream()
                .anyMatch(i->requiredRoles
                        .stream()
                        .anyMatch(x-> x.equalsIgnoreCase(i.getAuthority())));

        if(!hasAnyMatch)
          throw new NotAuthorizedException();
      }
    }
    return next.invoke();
  }
}