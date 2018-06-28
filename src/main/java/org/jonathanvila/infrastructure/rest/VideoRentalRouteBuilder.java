package org.jonathanvila.infrastructure.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.jonathanvila.domain.model.Customer;
import org.jonathanvila.domain.model.Film;
import org.jonathanvila.domain.model.FilmType;
import org.jonathanvila.domain.model.Rental;
import org.jonathanvila.domain.service.CustomerService;
import org.jonathanvila.domain.service.FilmService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.ArrayList;

@Singleton
public class VideoRentalRouteBuilder extends RouteBuilder {

    private final FilmService filmService;
    private final CustomerService customerService;

    @Inject
    public VideoRentalRouteBuilder(FilmService filmService, CustomerService customerService) {
        this.filmService = filmService;
        this.customerService = customerService;
    }

    @PostConstruct
    public void populateInitialData() {
        filmService.add(Film.builder().id(1L).name("Star Wars : The Last Jedi").filmType(FilmType.NewRelease).build());
        filmService.add(Film.builder().id(2L).name("Goonies").filmType(FilmType.Old).build());
        filmService.add(Film.builder().id(3L).name("Transformers").filmType(FilmType.Regular).build());

        customerService.add(Customer.builder().name("Jonathan").bonus(5).id(1L).rentals(new ArrayList<Rental>() {{
            add(Rental.builder().film(filmService.get(1L)).start(LocalDate.now()).finish(LocalDate.now().plusDays(3)).build());
            add(Rental.builder().film(filmService.get(2L)).start(LocalDate.now().minusDays(10)).finish(LocalDate.now().minusDays(8)).build());
        }}).build());

        customerService.add(Customer.builder().name("Cristina").bonus(2).id(1L).rentals(new ArrayList<Rental>() {{
            add(Rental.builder().film(filmService.get(1L)).start(LocalDate.now()).finish(LocalDate.now().plusDays(3)).build());
            add(Rental.builder().film(filmService.get(3L)).start(LocalDate.now().minusDays(10)).finish(LocalDate.now().minusDays(8)).build());
        }}).build());
    }

    @Override
    public void configure() {
        restConfiguration().component("restlet").host("localhost").port(8080).bindingMode(RestBindingMode.auto);

        filmApiBuilder();
        customerApiBuilder();
    }

    private void filmApiBuilder() {
        rest("/video-rental/api")
                .get("/film/{id}").to("direct:film-get")
                .get("/film").to("direct:film-getall")
                .post("/film").to("direct:film-post")
                .put("/film").to("direct:film-put");
    }

    private void customerApiBuilder() {
        rest("/video-rental/api")
                .get("/customer/{id}").to("direct:customer-get")
                .get("/customer").to("direct:customer-getall")
                .post("/customer").to("direct:customer-post")
                .put("/customer").to("direct:customer-put");
    }

    private void rentalApiBuilder() {
        rest("/video-rental/api")
                .get("/rental/{customer_id}").to("direct:rental-get")
                .get("/rental").to("direct:rental-getall")
                .post("/rental").to("direct:rental-post")
                .put("/rental").to("direct:rental-put");
    }


}
