package org.air.shopping.domain;

import java.util.List;
import java.util.stream.IntStream;

import static org.air.shopping.domain.DateFixtures.aZonedDateTime;
import static org.air.shopping.domain.LocationFixtures.aLocation;
import static org.air.shopping.domain.Randoms.oneOf;

public class DepartureFixtures {

    private static List<Departure> sampleDepartures = IntStream.range(0, 10)
            .mapToObj(i -> Departure.of(aLocation(), aZonedDateTime()))
            .toList();

    public static Departure aDeparture() {
        return oneOf(sampleDepartures);
    }

}
