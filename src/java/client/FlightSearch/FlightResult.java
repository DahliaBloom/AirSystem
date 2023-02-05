package client.FlightSearch;

import client.FlightData.FlightData;
import client.ServerCall.ServerCall;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class FlightResult {

    private FlightResult flightResult() {
        return null;
    }

    public static ArrayList<FlightData> searchResults(String from, String to) {
        ArrayList<FlightData> flights = new ArrayList<>();
        JSONArray json = Objects.requireNonNull(ServerCall.getJsonFrom("http://localhost:8080/flight/get")).getJSONArray("flights");
        for (int i = 0; i < json.length(); i++) {
            JSONObject el = json.getJSONObject(i);
            String destination = el.getJSONObject("destination").getString("name");
            String start = el.getString("departure");
            String arrival = el.getString("arrival");
            String seat = String.valueOf(el.getInt("seat"));
            String terminal = String.valueOf(el.getInt("terminal"));
            String gate = el.getString("gate");
            if (destination.equals(to) || to.equals("-")) flights.add(new FlightData(destination, start, arrival, seat, terminal, gate));
        }
        return flights;
    }

}
