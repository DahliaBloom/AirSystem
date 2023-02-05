package client.FlightData;

import client.FlightSearch.FlightResult;
import client.WeatherData.Weather;

import static client.Location.Munich;

public class FlightData {

    public final String destination;
    public final Weather weather;
    public final String startTime;
    public final String arrivalTime;
    public final String seat;
    public final String terminal;
    public final String gate;

    public FlightData(String destination, String startTime, String arrivalTime, String seat, String terminal, String gate) {
        this.destination = destination;
        this.weather = Weather.getInstance(Munich);
        this.startTime = startTime;
        this.arrivalTime = arrivalTime;
        this.seat = seat;
        this.terminal = terminal;
        this.gate = gate;
    }

    public static FlightData getCurrentFlight() {
        return FlightResult.searchResults("Munich", "-").get(0);
    }
}
