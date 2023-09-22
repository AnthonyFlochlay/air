package org.air.shopping.domain;

import java.util.List;
import java.util.stream.Stream;

public class AirlineFixtures {

    private static final List<Airline> sampleAirlines = Stream.of(
                    "American Airlines", "British Airways", "Delta Air Lines", "Qatar Airways", "Singapore Airlines", "Air France")
            .map(Airline::of)
            .toList();

    public static List<Airline> someAirlines() {
        return sampleAirlines;
    }

    public static Airline anAirline() {
        return Randoms.oneOf(sampleAirlines);
    }
}
