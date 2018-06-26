package org.jonathanvila.infrastructure.repository.impl;

import org.jonathanvila.domain.model.Customer;
import org.jonathanvila.infrastructure.repository.CustomerRepository;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Named("customerMemoryRepository")
public class CustomerMemoryRepository implements CustomerRepository {
    private List<Customer> customerList = new ArrayList<>();

    public CustomerMemoryRepository() {
        customerList.add(Customer.builder().bonus(0).name("Customer Test").build());
    }

    @Override
    public List<Customer> getAll() {
        return customerList;
    }
}
