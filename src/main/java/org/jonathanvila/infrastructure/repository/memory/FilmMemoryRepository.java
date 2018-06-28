package org.jonathanvila.infrastructure.repository.memory;

import lombok.extern.slf4j.Slf4j;
import org.jonathanvila.domain.model.Film;
import org.jonathanvila.infrastructure.repository.FilmRepository;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Singleton
@Named("filmMemoryRepository")
@Slf4j
public class FilmMemoryRepository implements FilmRepository {
    private List<Film> filmList = new ArrayList<>();

    @Override
    public List<Film> getAll() {
        return filmList;
    }

    @Override
    public Long add(Film film) {
        return filmList.stream()
                .filter(e-> e.getName().trim().equalsIgnoreCase(film.getName().trim()))
                .findFirst()
                .orElse(appendToList(film))
                .getId();
    }

    private Film appendToList(Film film) {
        filmList.add(setMaxIdToFilm(film));
        return film;
    }

    private Film setMaxIdToFilm(Film film) {
        film.setId(filmList.stream().max(Comparator.comparing(Film::getId)).map(e -> e.getId() + 1).orElse(1L));
        return film;
    }

    @Override
    public Film get(Long id) {
        log.debug("film->get, id = {}", id);
        return filmList.stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
