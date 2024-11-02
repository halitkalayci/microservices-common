package io.github.halitkalayci.core.exception.detail.custom;

import io.github.halitkalayci.core.exception.detail.ProblemDetails;

public class NotAuthorizedProblemDetails extends ProblemDetails {
  public NotAuthorizedProblemDetails() {
    super("Authorization Error","You dont have access to do this.","NotAuthorizedException");
  }
}