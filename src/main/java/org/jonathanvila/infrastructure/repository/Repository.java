package org.jonathanvila.infrastructure.repository;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();

    Long add(T customer);

    T get(Long id);
}
