package org.jonathanvila.infrastructure.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;

import javax.inject.Singleton;

@Singleton
public class CustomerRouteBuilder extends RouteBuilder {

    @Override
    public void configure()  {
      from("direct:customer-getall").bean("customerService", "getAll").marshal().json(JsonLibrary.Jackson);
      from("direct:customer-get").bean("customerService", "get(${header.id})").marshal().json(JsonLibrary.Jackson);
      from("direct:customer-add").bean("customerService", "add").marshal().json(JsonLibrary.Jackson);
    }
}
