package io.github.halitkalayci.customerservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IndividualCustomer extends Customer {
  private String firstName;
  private String lastName;
}