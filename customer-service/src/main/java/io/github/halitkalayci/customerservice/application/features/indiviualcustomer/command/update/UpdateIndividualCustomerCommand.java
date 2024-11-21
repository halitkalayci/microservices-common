package io.github.halitkalayci.customerservice.application.features.indiviualcustomer.command.update;

import an.awesome.pipelinr.Command;
import io.github.halitkalayci.core.application.pipeline.authorization.AuthenticatedRequest;
import io.github.halitkalayci.core.security.util.SecurityUtils;
import io.github.halitkalayci.customerservice.application.features.indiviualcustomer.mapper.IndiviualCustomerMapper;
import io.github.halitkalayci.customerservice.application.features.indiviualcustomer.rules.IndividualCustomerBusinessRules;
import io.github.halitkalayci.customerservice.domain.IndividualCustomer;
import io.github.halitkalayci.customerservice.persistence.IndividualCustomerRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerCommand implements Command<UpdatedIndividualCustomerResponse>, AuthenticatedRequest {
  @NotNull
  private UUID id;
  @NotBlank
  @Length(min = 3)
  private String firstName;
  private String lastName;
  private String phone;

  @Component
  @RequiredArgsConstructor
  public static class UpdateIndividualCustomerCommandHandler implements Command.Handler<UpdateIndividualCustomerCommand, UpdatedIndividualCustomerResponse>
  {
    private final IndiviualCustomerMapper indiviualCustomerMapper;
    private final IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerBusinessRules individualCustomerBusinessRules;

    @Override
    public UpdatedIndividualCustomerResponse handle(UpdateIndividualCustomerCommand updateIndividualCustomerCommand) {
      updateIndividualCustomerCommand.setId(SecurityUtils.getLoggedInUserId());

      individualCustomerBusinessRules.individualCustomerShouldExistById(updateIndividualCustomerCommand.getId());

      IndividualCustomer customerToUpdate = indiviualCustomerMapper
              .createCustomerFromUpdateCommand(updateIndividualCustomerCommand);

      customerToUpdate = individualCustomerRepository.save(customerToUpdate);


      return indiviualCustomerMapper.createUpdateResponseFromCustomer(customerToUpdate);
    }
  }
}
