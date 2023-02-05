package server.service;

import common.model.Flight;

import java.util.*;

public class FlightCollection {
    private final ArrayList<Flight> flightList;

    public FlightCollection(){
        flightList = new ArrayList<>();
    }

    //saves a Flight to the collection
    public Flight saveFlight(Flight flight){
        var optionalFlight =flightList.stream().filter(existingFlight -> existingFlight.getFlightID()
                .equals(flight.getFlightID())).findFirst();
        if (optionalFlight.isEmpty()){
            //erzeugt einen neuen Flug, falls der Flug noch nicht vorhanden ist
            flight.setFlightID(UUID.randomUUID());
            flightList.add(flight);
            return flight;
        }
        else{
            //Updated die existierenden Parameter falls der Flug schon vorhanden ist
            var existingFlight =optionalFlight.get();
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
    //deletes a flight
    public boolean deleteFlight(UUID flightId){
        return this.flightList.removeIf(flight -> flight.getFlightID().equals(flightId));
    }
    //gets all Flights
    public List<Flight> getAllFlights(){
        return Collections.unmodifiableList(this.flightList);
    }
    //get a specific flight from the collection
    public Flight getFlight(UUID uuid){
        var optionalFlight =flightList.stream().filter(existingFlight -> existingFlight.getFlightID()
                .equals(uuid)).findFirst();
        if(optionalFlight.isEmpty()){
            return null;
        }
        return optionalFlight.get();
    }
    //delays the given flight
    public Flight delayFlight(Flight flight){
        flight.setDelayed(true);
        return saveFlight(flight);
    }
    //cancels the given flight
    public Flight cancelFlight(Flight flight){
        flight.setCancelled(true);
        return saveFlight(flight);
    }
}
