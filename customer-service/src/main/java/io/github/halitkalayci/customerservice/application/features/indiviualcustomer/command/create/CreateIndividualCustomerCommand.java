package io.github.halitkalayci.customerservice.application.features.indiviualcustomer.command.create;

import an.awesome.pipelinr.Command;
import io.github.halitkalayci.customerservice.application.features.indiviualcustomer.mapper.IndiviualCustomerMapper;
import io.github.halitkalayci.customerservice.domain.IndividualCustomer;
import io.github.halitkalayci.customerservice.persistence.IndividualCustomerRepository;
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
  private String email;
  private String phone;

  @Component
  @RequiredArgsConstructor
  public static class CreateIndividualCustomerCommandHandler implements
          Command.Handler<CreateIndividualCustomerCommand, CreatedIndividualCustomerResponse>
  {
    private final IndiviualCustomerMapper indiviualCustomerMapper;
    private final IndividualCustomerRepository individualCustomerRepository;

    private final StreamBridge streamBridge;

    @Override
    public CreatedIndividualCustomerResponse handle(CreateIndividualCustomerCommand createIndividualCustomerCommand) {
      IndividualCustomer individualCustomer =
              indiviualCustomerMapper
              .createCustomerFromCommand(createIndividualCustomerCommand);

      individualCustomer = individualCustomerRepository.save(individualCustomer);

      streamBridge.send("sendCustomerEvent-out-0", "merhaba");

      return indiviualCustomerMapper.createIndividualCustomerResponse(individualCustomer);
    }
  }
}
