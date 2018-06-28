package org.jonathanvila.infrastructure.repository.memory;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.jonathanvila.domain.model.Customer;
import org.jonathanvila.domain.model.Rental;
import org.jonathanvila.domain.service.FilmService;
import org.jonathanvila.infrastructure.repository.CustomerRepository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Singleton
@Named("customerMemoryRepository")
@Slf4j public class CustomerMemoryRepository implements CustomerRepository {
    private List<Customer> customerList = new ArrayList<>();

    @Override
    public List<Customer> getAll() {
        return customerList;
    }

    @Override
    public Long add(Customer customer) {
        customer.setId(customerList.stream().max(Comparator.comparing(Customer::getId)).map(e -> e.getId() + 1).orElse(1L));
        customerList.add(customer);
        return customer.getId();
    }

    @Override
    public Customer get(Long id) {
        log.debug("customer->get, id = {}", id);
        return customerList.stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
