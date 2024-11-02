package io.github.halitkalayci.customerservice.application.features.indiviualcustomer.mapper;

import io.github.halitkalayci.customerservice.application.features.indiviualcustomer.command.create.CreateIndividualCustomerCommand;
import io.github.halitkalayci.customerservice.application.features.indiviualcustomer.command.create.CreatedIndividualCustomerResponse;
import io.github.halitkalayci.customerservice.application.features.indiviualcustomer.command.update.UpdateIndividualCustomerCommand;
import io.github.halitkalayci.customerservice.application.features.indiviualcustomer.command.update.UpdatedIndividualCustomerResponse;
import io.github.halitkalayci.customerservice.domain.IndividualCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class IndiviualCustomerMapper
{
  public abstract IndividualCustomer createCustomerFromCommand(CreateIndividualCustomerCommand createIndividualCustomerCommand);
  public abstract  CreatedIndividualCustomerResponse createIndividualCustomerResponse(IndividualCustomer individualCustomer);
  @Mapping(target="id",ignore = true)
  public abstract IndividualCustomer createCustomerFromUpdateCommand(UpdateIndividualCustomerCommand updateIndividualCustomerCommand);
  public abstract UpdatedIndividualCustomerResponse createUpdateResponseFromCustomer(IndividualCustomer customer);
}
