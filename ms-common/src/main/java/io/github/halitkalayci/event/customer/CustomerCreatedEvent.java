package io.github.halitkalayci.event.customer;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerCreatedEvent {
  private UUID id;
  private String email;
  private String firstName;
  private String lastName;
}
