package io.github.halitkalayci.core.application.pipeline.authorization;

import java.util.List;

public interface AuthorizedRequest {
  List<String> getRequiredRoles();
}
