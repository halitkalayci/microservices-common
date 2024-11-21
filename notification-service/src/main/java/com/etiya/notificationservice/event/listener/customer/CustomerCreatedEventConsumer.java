package com.etiya.notificationservice.event.listener.customer;

import io.github.halitkalayci.event.customer.CustomerCreatedEvent;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component("processCustomerCreatedEvent")
public class CustomerCreatedEventConsumer implements Consumer<CustomerCreatedEvent> {

  @Override
  public void accept(CustomerCreatedEvent event) {
    System.out.println("New customer created, sending welcome email: " + event.toString());
  }
}