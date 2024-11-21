package io.github.halitkalayci.customerservice.infrastructure.client;

import io.github.halitkalayci.dto.auth.request.RegisterRequest;
import io.github.halitkalayci.dto.auth.response.RegisterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "identity-service")
public interface IdentityServiceClient {
  @PostMapping("/api/v1/auth/register")
  RegisterResponse register(@RequestBody RegisterRequest registerRequest);
}
