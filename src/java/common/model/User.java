package common.model;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class User {
    private String username;
    private String password;
    private final UUID uuid;
    private final ArrayList<Flight> bookedFlights;
    private final ArrayList<Rewards> rewards;
    private final ArrayList<Trip> bookmarkedTrips;
    private final ArrayList<Notification> notifications;
    private final ArrayList<PlaceOfInterest> favoritedPlaces;

    public User(String userName, String password) {
        this.username = userName;
        this.password = password;
        this.uuid = UUID.nameUUIDFromBytes(userName.getBytes(StandardCharsets.UTF_8));
        bookedFlights = new ArrayList<>();
        rewards = new ArrayList<>();
        bookmarkedTrips = new ArrayList<>();
        notifications = new ArrayList<>();
        favoritedPlaces = new ArrayList<>();

    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Flight saveBookedFlight(Flight flight) {
        var optionalFlight = bookedFlights.stream().filter(existingFlight -> existingFlight.getFlightID()
                .equals(flight.getFlightID())).findFirst();
        if (optionalFlight.isEmpty()) {
            flight.setFlightID(UUID.randomUUID());
            bookedFlights.add(flight);
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
            existingFlight.setDelayed(flight.isDelayed());
            existingFlight.setCancelled(flight.isCancelled());
            return existingFlight;
        }
    }

    public boolean removeBookedFlight(UUID flightId) {
        return bookedFlights.removeIf(flight -> flight.getFlightID().equals(flightId));
    }


    public boolean addReward(Rewards rewards) {
        return this.rewards.add(rewards);
    }

    public boolean removeReward(Rewards rewards) {
        return this.rewards.remove(rewards);
    }

    public boolean addTrip(Trip trip) {
        return bookmarkedTrips.add(trip);
    }

    public boolean removeTrip(Trip trip) {
        return bookmarkedTrips.remove(trip);
    }

    private boolean bookTrip(Trip trip) {
        if (bookmarkedTrips.contains(trip)) {
            trip.getFlightList().forEach(t -> saveBookedFlight(t));
            removeTrip(trip);
            return true;
        }
        return false;
    }

    public List<Flight> getBookedFlights() {
        return Collections.unmodifiableList(this.bookedFlights);
    }

    public List<Rewards> getRewards() {
        return Collections.unmodifiableList(this.rewards);
    }

    public List<Trip> getBookmarkedTrips() {
        return Collections.unmodifiableList(this.bookmarkedTrips);
    }

    public List<PlaceOfInterest> getFavoritePlaces() {
        return Collections.unmodifiableList(favoritedPlaces);
    }

    public boolean addNotification(Notification notification) {
        return notifications.add(notification);
    }

    public boolean removeNotification(Notification notification) {
        return notifications.remove(notification);
    }

    public List<Notification> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }


}
