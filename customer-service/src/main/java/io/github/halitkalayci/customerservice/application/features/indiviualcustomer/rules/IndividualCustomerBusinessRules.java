package io.github.halitkalayci.customerservice.application.features.indiviualcustomer.rules;

import io.github.halitkalayci.core.exception.types.BusinessException;
import io.github.halitkalayci.customerservice.application.features.indiviualcustomer.resources.ErrorMessageCodes;
import io.github.halitkalayci.customerservice.persistence.IndividualCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IndividualCustomerBusinessRules {
  private final IndividualCustomerRepository individualCustomerRepository;

  public void individualCustomerShouldExistById(UUID id)
  {
    individualCustomerRepository
            .findById(id)
            .orElseThrow(() -> new BusinessException(ErrorMessageCodes.NOTFOUND_BYID));
  }
}
