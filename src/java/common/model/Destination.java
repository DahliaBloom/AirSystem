package common.model;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

public class Destination {
    private UUID uuid;
    private ArrayList<PlaceOfInterest> placeOfInterests;
    private String name;

    public Destination(String name) {
        this.name = name;
        this.uuid = UUID.nameUUIDFromBytes(name.getBytes(StandardCharsets.UTF_8));
        placeOfInterests = (ArrayList<PlaceOfInterest>) PlaceOfInterest.getPOIs(name);
        for(int i = 0; i < placeOfInterests.size(); i++){
            placeOfInterests.get(i).setWeatherData(new WeatherData(name));
        }
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ArrayList<PlaceOfInterest> getPlaceOfInterests() {
        return placeOfInterests;
    }

    public void setPlaceOfInterests(ArrayList<PlaceOfInterest> placeOfInterests) {
        this.placeOfInterests = placeOfInterests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPlaceOfInterest(PlaceOfInterest placeOfInterest) {
        placeOfInterests.add(placeOfInterest);
    }

    public static Destination generateRandomDestination() {
        Destination destination = new Destination("a");
        destination.placeOfInterests.add(new PlaceOfInterest(null, ",b", "c"));
        return destination;
    }
}
