package org.air.shopping.domain;

import java.time.ZonedDateTime;

public record Departure(Location origin, ZonedDateTime departureTime) {

    public static Departure of(Location origin, ZonedDateTime time) {
        return new Departure(origin, time);
    }

    public Departure withOrigin(Location otherOrigin) {
        return new Departure(otherOrigin, this.departureTime);
    }

    public Departure withStartingTime(ZonedDateTime zonedDateTime) {
        return new Departure(origin, zonedDateTime);
    }
}
