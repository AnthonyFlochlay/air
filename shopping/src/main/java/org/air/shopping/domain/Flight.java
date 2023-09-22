package org.air.shopping.domain;

public record Flight(Departure departure, Location destination, Airline airline) {
    public static Flight of(Departure departure, Location destination, Airline airline) {
        return new Flight(departure, destination, airline);
    }

    public Location origin() {
        return departure.origin();
    }
}
