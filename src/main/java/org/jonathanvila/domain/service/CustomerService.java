package org.jonathanvila.domain.service;

import org.jonathanvila.domain.model.Customer;
import org.jonathanvila.infrastructure.repository.CustomerRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;
import javax.inject.Singleton;
import java.util.List;

@Singleton
@Named("customerBean")
public class CustomerService {
    @Inject
    @Named("customerMemoryRepository")
    private CustomerRepository repository;

    public List<Customer> getAll() {
        return repository.getAll();
    }
}
