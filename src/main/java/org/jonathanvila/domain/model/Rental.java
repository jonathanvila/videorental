package org.jonathanvila.domain.model;

import java.time.Instant;

public class Rental {
    private Film film;
    private Customer customer;
    private Instant start;
    private Instant finish;
}
