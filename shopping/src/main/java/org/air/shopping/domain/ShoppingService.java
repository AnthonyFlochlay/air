package org.air.shopping.domain;

import org.air.shopping.domain.spi.FlightInventory;

import java.util.List;

public class ShoppingService {
    private final FlightInventory flightInventory;

    public ShoppingService(FlightInventory flightInventory) {
        this.flightInventory = flightInventory;
    }

    public List<Flight> search(SearchByDeparture searchByDeparture) {
        System.out.printf("Searching flights from %s to %s%n", searchByDeparture.departure(), searchByDeparture.destination());
        return flightInventory.search(searchByDeparture);
    }
}
