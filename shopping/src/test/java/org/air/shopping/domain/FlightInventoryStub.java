package org.air.shopping.domain;

import org.air.shopping.domain.spi.FlightInventory;

import java.util.List;

public class FlightInventoryStub implements FlightInventory {
    private final List<Flight> flights;

    public FlightInventoryStub(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public List<Flight> search(SearchByDeparture searchByDeparture) {
        return flights.stream()
                .filter(f -> f.origin().equals(searchByDeparture.origin()))
                .filter(f -> f.destination().equals(searchByDeparture.destination()))
                .filter(f -> f.departureDateTime().isAfter(searchByDeparture.departureTime()))
                .toList();
    }
}
