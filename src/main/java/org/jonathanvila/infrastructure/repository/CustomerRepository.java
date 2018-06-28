package org.jonathanvila.infrastructure.repository;

import org.jonathanvila.domain.model.Customer;

import java.util.List;

public interface CustomerRepository extends Repository<Customer> {
     List<Customer> getAll();

    Long add(Customer customer);

    Customer get(Long id);
}
