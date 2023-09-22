package org.air.shopping.domain;

import org.air.shopping.domain.spi.FlightInventory;

import java.util.List;

public class ShoppingService {
    private final FlightInventory flightInventory;

    public ShoppingService(FlightInventory flightInventory) {
        this.flightInventory = flightInventory;
    }

    public List<Flight> search(Location origin, Location destination) {
        return flightInventory.search(origin, destination);
    }
}
