package io.github.halitkalayci.core.exception.detail.custom;


import io.github.halitkalayci.core.exception.detail.ProblemDetails;

public class NotAuthenticatedProblemDetails extends ProblemDetails {
  public NotAuthenticatedProblemDetails() {
    super("Authentication Error","You are not authenticated","NotAuthenticatedException");
  }
}
