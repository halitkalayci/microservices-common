package io.github.halitkalayci.core.exception.handler;

import io.github.halitkalayci.core.exception.detail.custom.NotAuthorizedProblemDetails;
import io.github.halitkalayci.core.exception.types.NotAuthorizedException;
import io.github.halitkalayci.core.exception.detail.custom.NotAuthenticatedProblemDetails;
import io.github.halitkalayci.core.exception.types.NotAuthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({NotAuthenticatedException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public NotAuthenticatedProblemDetails handleAuthenticationException(NotAuthenticatedException exception){
    return new NotAuthenticatedProblemDetails();
  }

  @ExceptionHandler({NotAuthorizedException.class})
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public NotAuthorizedProblemDetails handleAuthorizeException(NotAuthorizedException exception)
  {
    return new NotAuthorizedProblemDetails();
  }
}