package org.air.shopping.domain;

import java.util.List;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static org.air.shopping.domain.Randoms.oneOf;

public class LocationFixtures {

    private static final List<Location> sampleLocations = Stream.of(
                    "Berlin", "London", "New York", "Paris", "Shangai", "Tokyo")
            .map(Location::new)
            .toList();

    public static List<Location> someLocations() {
        return sampleLocations;
    }

    public static Location aLocation() {
        return oneOf(sampleLocations);
    }

    public static Location aLocationOtherThan(Location excluded) {
        return oneOf(
                someLocations().stream().filter(not(f -> f.equals(excluded))).toList()
        );
    }
}
