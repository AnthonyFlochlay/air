package org.air.shopping.domain;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static org.air.shopping.domain.AirlineFixtures.anAirline;
import static org.air.shopping.domain.DepartureFixtures.aDeparture;
import static org.air.shopping.domain.FlightFixtures.*;
import static org.air.shopping.domain.LocationFixtures.aLocation;
import static org.air.shopping.domain.Randoms.aDateTimeBetween;
import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingTest {

    @Test
    void should_return_flights_starting_from_origin_after_departure_time_and_going_to_destination() {
        // Given
        var searchDeparture = aDeparture();
        var searchDestination = aLocation();
        var relevantFLights = relevantFlightsOf(searchDeparture, searchDestination);
        var shoppingService = new ShoppingService(new FlightInventoryStub(relevantFLights));
        // When
        var flights = shoppingService.search(searchDeparture, searchDestination);
        // Then
        assertThat(flights)
                .allSatisfy(leavesFrom(searchDeparture.origin()))
                .allSatisfy(leavesAfter(searchDeparture.dateTime()))
                .allSatisfy(arrivesIn(searchDestination))
                .isNotEmpty()
                .extracting(Flight::airline)
                .containsExactlyElementsOf(relevantFLights.stream().map(Flight::airline).toList());
    }

    private List<Flight> relevantFlightsOf(Departure searchDeparture, Location searchDestination) {
        return IntStream.range(0, 10)
                .mapToObj(i -> aSearchedFlightOf(searchDeparture, searchDestination))
                .toList();
    }

    private Flight aSearchedFlightOf(Departure searchDeparture, Location searchDestination) {
        ZonedDateTime dateTime = searchDeparture.dateTime();
        return Flight.of(
                searchDeparture.withStartingTime(
                        aDateTimeBetween(dateTime, dateTime.plusMonths(1))
                ),
                searchDestination,
                anAirline()
        );
    }

    private Consumer<? super Flight> leavesFrom(Location origin) {
        return flight -> assertThat(flight.origin()).isEqualTo(origin);
    }

    private Consumer<? super Flight> leavesAfter(ZonedDateTime departureTime) {
        return flight -> assertThat(flight.departureDateTime()).isAfterOrEqualTo(departureTime);
    }

    private Consumer<? super Flight> arrivesIn(Location destination) {
        return flight -> assertThat(flight.destination()).isEqualTo(destination);
    }

    @Test
    void should_not_return_irrelevant_flights() {
        // Given
        Departure searchDeparture = aDeparture();
        Location searchDestination = aLocation();
        List<Flight> notRelevantFLights = notRelevantFlightsOf(searchDeparture, searchDestination);
        var shoppingService = new ShoppingService(new FlightInventoryStub(notRelevantFLights));
        // When
        var flights = shoppingService.search(searchDeparture, searchDestination);
        // Then
        assertThat(flights).isEmpty();
    }

    private List<Flight> notRelevantFlightsOf(Departure searchDeparture, Location searchDestination) {
        var searchedFlight = aSearchedFlightOf(searchDeparture, searchDestination);
        return List.of(
                sameFlightWithAnotherOrigin(searchedFlight),
                sameFlightWithAnotherDestination(searchedFlight),
                searchedFlight.withDepartureTime(searchDeparture.dateTime().minusHours(1))
        );
    }

}
