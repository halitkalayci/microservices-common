package io.github.halitkalayci.customerservice.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class CorporateCustomer extends Customer {
  private String companyName;
  private String taxNumber;
}