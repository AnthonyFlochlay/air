package org.air.shopping.domain;

public record Flight(Location origin, Location destination, Airline airline) {
    public static Flight of(Location origin, Location destination, Airline airline) {
        return new Flight(origin, destination, airline);
    }
}
