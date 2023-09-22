package org.air.shopping.domain;

import java.util.List;
import java.util.stream.IntStream;

import static org.air.shopping.domain.AirlineFixtures.anAirline;
import static org.air.shopping.domain.LocationFixtures.aLocation;
import static org.air.shopping.domain.Randoms.oneOf;

public class FlightFixtures {
    private static final List<Flight> sampleFlights = IntStream.range(0, 30)
            .mapToObj(i -> Flight.of(aLocation(), aLocation(), anAirline()))
            .toList();

    public static List<Flight> someFlights() {
        return sampleFlights;
    }

    private static Flight aFlight() {
        return oneOf(sampleFlights);
    }
}
