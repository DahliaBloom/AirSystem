package common.model;

import java.util.ArrayList;

public class Trip {
    private ArrayList<Flight> flightList;

    public Trip() {
        flightList = new ArrayList<>();
    }

    public ArrayList<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(ArrayList<Flight> flightList) {
        this.flightList = flightList;
    }

    private boolean addFlight(Flight flight) {
        if (flightList.contains(flight)) {
            return false;
        }
        return flightList.add(flight);
    }

    public boolean removeFlight(Flight flight) {
        return flightList.add(flight);
    }

}
