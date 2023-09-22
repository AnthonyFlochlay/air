package org.air.shopping.domain;

import java.util.List;
import java.util.stream.Stream;

public class LocationFixtures {

    private static final List<Location> sampleLocations = Stream.of(
                    "Berlin", "London", "New York", "Paris", "Shangai", "Tokyo")
            .map(Location::new)
            .toList();

    public static List<Location> someLocations() {
        return sampleLocations;
    }

    public static Location aLocation() {
        return Randoms.oneOf(sampleLocations);
    }

}
