package org.jonathanvila.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Customer {
    private Long id;
    private String name;
    private long bonus;
    private List<Rental> rentals;
}
