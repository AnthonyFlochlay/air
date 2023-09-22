package org.air.shopping.domain;

import java.time.ZonedDateTime;

public record Departure(Location origin, ZonedDateTime time) {

    public static Departure of(Location origin, ZonedDateTime time) {
        return new Departure(origin, time);
    }
}
