package org.jonathanvila.infrastructure.repository;

import org.jonathanvila.domain.model.Customer;

import java.util.List;

public interface CustomerRepository {
    public List<Customer> getAll();
}
