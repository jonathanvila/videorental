package org.jonathanvila.infrastructure.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import javax.inject.Singleton;

@Singleton
public class FilmRouteBuilder extends RouteBuilder {

    @Override
    public void configure()  {
      from("direct:film-getall").bean("filmService", "getAll").marshal().json(JsonLibrary.Jackson);
      from("direct:film-get").bean("filmService", "get(${header.id})").marshal().json(JsonLibrary.Jackson);
      from("direct:film-add").bean("filmService", "add").marshal().json(JsonLibrary.Jackson);
    }
}
