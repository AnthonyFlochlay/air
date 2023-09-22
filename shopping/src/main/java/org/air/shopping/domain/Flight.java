package org.air.shopping.domain;

import java.time.ZonedDateTime;

public record Flight(Departure departure, Location destination, Airline airline) {
    public static Flight of(Departure departure, Location destination, Airline airline) {
        return new Flight(departure, destination, airline);
    }

    public Location origin() {
        return departure.origin();
    }

    public ZonedDateTime departureDateTime() {
        return departure.dateTime();
    }

    public Flight withOrigin(Location otherOrigin) {
        return new Flight(departure.withOrigin(otherOrigin), this.destination, this.airline);
    }

    public Flight withDestination(Location otherDestination) {
        return new Flight(this.departure, otherDestination, this.airline);
    }

    public Flight withDepartureTime(ZonedDateTime otherDateTime) {
        return new Flight(this.departure.withStartingTime(otherDateTime), this.destination, this.airline);
    }
}
