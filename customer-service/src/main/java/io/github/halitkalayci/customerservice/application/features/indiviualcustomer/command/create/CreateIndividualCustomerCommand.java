package io.github.halitkalayci.customerservice.application.features.indiviualcustomer.command.create;

import an.awesome.pipelinr.Command;
import io.github.halitkalayci.customerservice.application.features.indiviualcustomer.mapper.IndiviualCustomerMapper;
import io.github.halitkalayci.customerservice.domain.IndividualCustomer;
import io.github.halitkalayci.customerservice.infrastructure.client.IdentityServiceClient;
import io.github.halitkalayci.customerservice.persistence.IndividualCustomerRepository;
import io.github.halitkalayci.dto.auth.request.RegisterRequest;
import io.github.halitkalayci.dto.auth.response.RegisterResponse;
import io.github.halitkalayci.event.customer.CustomerCreatedEvent;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerCommand implements Command<CreatedIndividualCustomerResponse> {
  @NotBlank
  @Length(min = 3)
  private String firstName;
  private String lastName;
  private String phone;
  private RegisterRequest user;

  @Component
  @RequiredArgsConstructor
  public static class CreateIndividualCustomerCommandHandler implements
          Command.Handler<CreateIndividualCustomerCommand, CreatedIndividualCustomerResponse>
  {
    private final IndiviualCustomerMapper indiviualCustomerMapper;
    private final IndividualCustomerRepository individualCustomerRepository;
    private final IdentityServiceClient identityServiceClient;
    private final StreamBridge streamBridge;

    @Override
    public CreatedIndividualCustomerResponse handle(CreateIndividualCustomerCommand createIndividualCustomerCommand) {
      RegisterResponse userResponse =
              identityServiceClient.register(createIndividualCustomerCommand.getUser());

      IndividualCustomer individualCustomer =
              indiviualCustomerMapper
              .createCustomerFromCommand(createIndividualCustomerCommand);

      individualCustomer.setId(userResponse.getId());
      individualCustomer = individualCustomerRepository.save(individualCustomer);

      streamBridge.send("customerCreatedEvent-out-0", new CustomerCreatedEvent(
              userResponse.getId(),
              userResponse.getEmail(),
              individualCustomer.getFirstName(),
              individualCustomer.getLastName()
      ));

      return indiviualCustomerMapper.createIndividualCustomerResponse(individualCustomer);
    }
  }
}
