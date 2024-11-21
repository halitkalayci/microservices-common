package io.github.halitkalayci.core.exception.detail.custom;


import io.github.halitkalayci.core.exception.detail.ProblemDetails;

public class InvalidCredentialsProblemDetails extends ProblemDetails {
  public InvalidCredentialsProblemDetails() {
    super("Invalid Credentials",
            "Invalid credentials. Please check username or password.",
            "InvalidCredentialsException");
  }
}