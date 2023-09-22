package org.air.shopping.domain;

import java.time.ZonedDateTime;

public record SearchByDeparture(Departure departure, Location destination) {
    public Location origin() {
        return departure.origin();
    }

    public ZonedDateTime departureTime() {
        return departure.departureTime();
    }
}