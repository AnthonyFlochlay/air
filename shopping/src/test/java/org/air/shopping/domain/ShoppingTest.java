package org.air.shopping.domain;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static org.air.shopping.domain.AirlineFixtures.anAirline;
import static org.air.shopping.domain.DepartureFixtures.aDeparture;
import static org.air.shopping.domain.FlightFixtures.sameFlightWithAnotherDestination;
import static org.air.shopping.domain.FlightFixtures.sameFlightWithAnotherOrigin;
import static org.air.shopping.domain.LocationFixtures.aLocationOtherThan;
import static org.air.shopping.domain.Randoms.aDateTimeBetween;
import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingTest {

    @Test
    void should_return_flights_starting_from_origin_after_departure_time_and_going_to_destination() {
        // Given
        var searchByDeparture = aSearchByDeparture();
        var relevantFLights = relevantFlightsOf(searchByDeparture);
        var shoppingService = new ShoppingService(new FlightInventoryStub(relevantFLights));
        // When
        var flights = shoppingService.search(searchByDeparture);
        // Then
        assertThat(flights)
                .allSatisfy(leavesFrom(searchByDeparture.origin()))
                .allSatisfy(leavesAfter(searchByDeparture.departureTime()))
                .allSatisfy(arrivesIn(searchByDeparture.destination()))
                .isNotEmpty()
                .extracting(Flight::airline)
                .containsExactlyElementsOf(relevantFLights.stream().map(Flight::airline).toList());
    }

    private static SearchByDeparture aSearchByDeparture() {
        var departure = aDeparture();
        return new SearchByDeparture(departure, aLocationOtherThan(departure.origin()));
    }

    private List<Flight> relevantFlightsOf(SearchByDeparture search) {
        return IntStream.range(0, 10)
                .mapToObj(i -> aSearchedFlightOf(search))
                .toList();
    }

    private Flight aSearchedFlightOf(SearchByDeparture search) {
        ZonedDateTime dateTime = search.departureTime();
        return Flight.of(
                search.departure().withStartingTime(
                        aDateTimeBetween(dateTime, dateTime.plusMonths(1))
                ),
                search.destination(),
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
        var search = aSearchByDeparture();
        List<Flight> notRelevantFLights = notRelevantFlightsOf(search);
        var shoppingService = new ShoppingService(new FlightInventoryStub(notRelevantFLights));
        // When
        var flights = shoppingService.search(search);
        // Then
        assertThat(flights).isEmpty();
    }

    private List<Flight> notRelevantFlightsOf(SearchByDeparture search) {
        var searchedFlight = aSearchedFlightOf(search);
        return List.of(
                sameFlightWithAnotherOrigin(searchedFlight),
                sameFlightWithAnotherDestination(searchedFlight),
                searchedFlight.withDepartureTime(search.departureTime().minusHours(1))
        );
    }

}
