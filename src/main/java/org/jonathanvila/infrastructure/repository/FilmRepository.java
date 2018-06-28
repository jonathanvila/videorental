package org.jonathanvila.infrastructure.repository;

import org.jonathanvila.domain.model.Film;

import java.util.List;

public interface FilmRepository extends Repository<Film> {
    List<Film> getAll();

    Long add(Film film);

    Film get(Long id);
}
