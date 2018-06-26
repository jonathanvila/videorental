package org.jonathanvila.infrastructure.camel;

import org.apache.camel.builder.RouteBuilder;

import javax.inject.Singleton;

@Singleton
public class FilmRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
      from("direct:film-getall").bean("customerService", "getAll");
      from("direct:film-get").bean("customerService", "get");


    }
}
