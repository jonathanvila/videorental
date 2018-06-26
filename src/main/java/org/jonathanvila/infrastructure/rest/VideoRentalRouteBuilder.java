package org.jonathanvila.infrastructure.rest;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import javax.inject.Inject;

public class VideoRentalRouteBuilder extends RouteBuilder {

    @Inject
    CamelContext camelContext;

    public void configure() {
        restConfiguration().component("restlet").host("localhost").port(8080).bindingMode(RestBindingMode.auto);

        filmApiBuilder();
        customerApiBuilder();
    }

    private void filmApiBuilder() {
        rest("/video-rental/api/film")
                .get("{id}").to("direct:film-get")
                .post().consumes("application/json").to("direct:film-post")
                .put().consumes("application/json").to("direct:film-put");
    }

    private void customerApiBuilder() {
        rest("/video-rental/api/customer")
                .get("{id}").to("direct:customer-get")
                .post().consumes("application/json").to("direct:customer-post")
                .put().consumes("application/json").to("direct:customer-put");
    }



}
