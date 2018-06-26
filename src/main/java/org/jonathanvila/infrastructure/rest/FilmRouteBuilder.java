package org.jonathanvila.infrastructure.rest;

import org.apache.camel.builder.RouteBuilder;

public class FilmRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
      from("direct:film-getall").bean("customerService", "getAll");
      from("direct:film-get").bean("customerService", "get");


    }
}
