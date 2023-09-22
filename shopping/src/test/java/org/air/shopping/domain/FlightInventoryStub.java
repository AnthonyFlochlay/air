package org.air.shopping.domain;

import org.air.shopping.domain.spi.FlightInventory;

import java.util.List;

public class FlightInventoryStub implements FlightInventory {
    private final List<Flight> flights;

    public FlightInventoryStub(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public List<Flight> search(Departure departure, Location destination) {
        return flights.stream()
                .filter(f -> f.origin().equals(departure.origin()))
                .filter(f -> f.destination().equals(destination))
                .filter(f -> f.departureDateTime().isAfter(departure.dateTime()))
                .toList();
    }
}
