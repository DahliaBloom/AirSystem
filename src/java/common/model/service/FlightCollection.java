package common.model.service;

import common.model.Flight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class FlightCollection {
    private final ArrayList<Flight> flightList;

    public FlightCollection() {
        flightList = new ArrayList<>();
    }

    public Flight saveFlight(Flight flight) {
        var optionalFlight = flightList.stream().filter(existingFlight -> existingFlight.getFlightID()
                .equals(flight.getFlightID())).findFirst();
        if (optionalFlight.isEmpty()) {
            flight.setFlightID(UUID.randomUUID());
            flightList.add(flight);
            return flight;
        } else {
            var existingFlight = optionalFlight.get();
            existingFlight.setFlightNumber(flight.getFlightNumber());
            existingFlight.setAirplaneType(flight.getAirplaneType());
            existingFlight.setArrival(flight.getArrival());
            existingFlight.setDeparture(flight.getDeparture());
            existingFlight.setGate(flight.getGate());
            existingFlight.setSeat(flight.getSeat());
            existingFlight.setTerminal(flight.getTerminal());
            existingFlight.setName(flight.getName());
            return existingFlight;
        }
    }

    public boolean deleteFlight(UUID flightId) {
        return this.flightList.removeIf(flight -> flight.getFlightID().equals(flightId));
    }

    public List<Flight> getFlights() {
        return Collections.unmodifiableList(this.flightList);
    }

    public Flight getFlight(UUID uuid) {
        var optionalFlight = flightList.stream().filter(existingFlight -> existingFlight.getFlightID()
                .equals(uuid)).findFirst();
        if (optionalFlight.isEmpty()) {
            return null;
        }
        return optionalFlight.get();
    }

    public Flight delayFlight(Flight flight) {
        flight.setDelayed(true);
        return saveFlight(flight);
    }

    public Flight cancelFlight(Flight flight) {
        flight.setCancelled(true);
        return saveFlight(flight);
    }
}
