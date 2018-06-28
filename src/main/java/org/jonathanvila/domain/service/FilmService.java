package org.jonathanvila.domain.service;

import org.jonathanvila.domain.model.Film;
import org.jonathanvila.infrastructure.repository.FilmRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

@Singleton
@Named("filmService")
public class FilmService {
    @Inject
    @Named("filmMemoryRepository")
    private FilmRepository repository;

    public List<Film> getAll() {
        return repository.getAll();
    }

    public Film get(Long id) {
      return repository.get(id);
    }

    public Long add(Film film) {
        return repository.add(film);
    }
}
