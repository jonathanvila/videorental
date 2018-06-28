package org.jonathanvila.domain.service;

import org.jonathanvila.domain.model.FilmType;
import org.jonathanvila.domain.model.Rental;
import org.jonathanvila.infrastructure.view.RentalView;

import java.time.Duration;
import java.time.LocalDate;

public class RentalService {
    private static final Integer baseRental = 30;
    private static final Integer premiumRental = 40;

    public Integer calcPrice(Rental rental) {
        LocalDate finish = getFinishDate(rental);
        int diffDays = getDiffDays(rental.getStart(), finish);

        switch (rental.getFilm().getFilmType()) {
         case NewRelease : return diffDays * premiumRental;
         case Regular: return baseRental + Math.abs(diffDays - 3) * premiumRental;
         default: return baseRental + Math.abs(diffDays - 5) * premiumRental;
        }
    }

    private LocalDate getFinishDate(Rental rental) {
        return rental.getReturned().isPresent() ? rental.getReturned().get() : rental.getFinish().isBefore(LocalDate.now()) ? rental.getFinish() : LocalDate.now();
    }

    private int getDiffDays(LocalDate start, LocalDate finish) {
        return Long.valueOf(Duration.between(start, finish).toDays()).intValue();
    }

    public RentalView rent(Rental rental) {
        return new RentalView();
    }


}
