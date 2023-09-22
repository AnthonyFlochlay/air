package org.air.shopping.domain.spi;

import org.air.shopping.domain.Flight;
import org.air.shopping.domain.Location;

import java.util.List;
import java.util.Locale;

public interface FlightInventory {
    List<Flight> search(Location origin, Location destination);
}
