package io.github.halitkalayci.core.exception.detail.custom;

import io.github.halitkalayci.core.exception.detail.ProblemDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Setter
public class ValidationProblemDetails extends ProblemDetails {
  private List<FieldError> errors;

  public ValidationProblemDetails(List<FieldError> errors) {
    super("Validation Violation", "You have invalid fields.", "ValidationException");
    this.errors = errors;
  }
}
