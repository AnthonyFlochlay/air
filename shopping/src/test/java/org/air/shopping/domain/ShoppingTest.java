package org.air.shopping.domain;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.air.shopping.domain.FlightFixtures.someFlights;
import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingTest {

    @Test
    void should_return_flights_starting_from_departure_and_going_to_destination() {
        // Given
        var existingFlights = someFlights();
        var shoppingService = new ShoppingService(new FlightInventoryStub(existingFlights));
        // When
        var existingflight = Randoms.oneOf(existingFlights);
        var flights = shoppingService.search(existingflight.departure(), existingflight.destination());
        // Then
        assertThat(flights)
                .allSatisfy(leavesFrom(existingflight.origin()))
                .allSatisfy(arrivesIn(existingflight.destination()))
                .isNotEmpty()
                .extracting(Flight::airline)
                .contains(existingflight.airline());
    }

    private Consumer<? super Flight> leavesFrom(Location origin) {
        return flight -> assertThat(flight.origin()).isEqualTo(origin);
    }

    private Consumer<? super Flight> arrivesIn(Location destination) {
        return flight -> assertThat(flight.destination()).isEqualTo(destination);
    }

}
