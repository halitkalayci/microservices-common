package io.github.halitkalayci.customerservice.application.features.indiviualcustomer.command.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedIndividualCustomerResponse {
  private UUID id;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
}
