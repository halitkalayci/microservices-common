package io.github.halitkalayci.customerservice.persistence;

import io.github.halitkalayci.customerservice.domain.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IndividualCustomerRepository
        extends JpaRepository<IndividualCustomer, UUID> {
}
