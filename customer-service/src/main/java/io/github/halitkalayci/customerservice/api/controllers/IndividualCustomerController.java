package io.github.halitkalayci.customerservice.api.controllers;

import an.awesome.pipelinr.Pipeline;
import io.github.halitkalayci.core.api.controllers.BaseController;
import io.github.halitkalayci.customerservice.application.features.indiviualcustomer.command.create.CreateIndividualCustomerCommand;
import io.github.halitkalayci.customerservice.application.features.indiviualcustomer.command.create.CreatedIndividualCustomerResponse;
import io.github.halitkalayci.customerservice.application.features.indiviualcustomer.command.update.UpdateIndividualCustomerCommand;
import io.github.halitkalayci.customerservice.application.features.indiviualcustomer.command.update.UpdatedIndividualCustomerResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/individual-customers")
public class IndividualCustomerController extends BaseController {


  public IndividualCustomerController(Pipeline pipeline) {
    super(pipeline);
  }

  @PostMapping
  public ResponseEntity<CreatedIndividualCustomerResponse> createCustomer(@RequestBody CreateIndividualCustomerCommand createIndividualCustomerCommand) {
    CreatedIndividualCustomerResponse response = createIndividualCustomerCommand.execute(pipeline);
    URI location = uriForCreatedResource("/{id}", response.getId());
    return ResponseEntity.created(location).body(response);
  }

  @PutMapping
  public ResponseEntity<UpdatedIndividualCustomerResponse> updateCustomer(@RequestBody UpdateIndividualCustomerCommand updateIndividualCustomerCommand)
  {
    UpdatedIndividualCustomerResponse response = updateIndividualCustomerCommand.execute(pipeline);
    return ResponseEntity.ok().body(response);
  }
}
