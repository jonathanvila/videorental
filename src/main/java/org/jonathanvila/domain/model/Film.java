package org.jonathanvila.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Film {
    private FilmType filmType;
    private Long id;
    private String name;
}
