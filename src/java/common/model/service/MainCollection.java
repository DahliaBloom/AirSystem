package common.model.service;

import common.model.Rewards;
import common.model.User;
import common.model.Destination;
import common.model.Flight;

import java.util.UUID;

public class MainCollection {
    private DestinationCollection destinationCollection;
    private FlightCollection flightCollection;
    private UserCollection userCollection;

    public MainCollection() {
        destinationCollection = new DestinationCollection();
        flightCollection = new FlightCollection();
        userCollection = new UserCollection();
    }

    public DestinationCollection getDestinationCollection() {
        return destinationCollection;
    }

    public void setDestinationCollection(DestinationCollection destinationCollection) {
        this.destinationCollection = destinationCollection;
    }

    public FlightCollection getFlightCollection() {
        return flightCollection;
    }

    public void setFlightCollection(FlightCollection flightCollection) {
        this.flightCollection = flightCollection;
    }

    public UserCollection getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(UserCollection userCollection) {
        this.userCollection = userCollection;
    }

    public Flight getFlight(UUID uuid) {
        return flightCollection.getFlight(uuid);
    }

    public Destination getDestination(UUID uuid) {
        return destinationCollection.getDestination(uuid);
    }

    public User getUser(UUID uuid) {
        return userCollection.findUser(uuid);
    }

    public Flight saveFlight(Flight flight) {
        return flightCollection.saveFlight(flight);
    }

    public Destination saveDestination(Destination destination) {
        return destinationCollection.saveDestination(destination);
    }

    public boolean createUser(String name, String password) {
        return userCollection.createUser(name, password);
    }

    public static void main(String[] args) {
        System.out.println("test");
        MainCollection mainCollection = new MainCollection();
        UserCollection userCollection = new UserCollection();
        userCollection.createUser("test", "abc");
        userCollection.login("test", "abc");
        userCollection.addReward(Rewards.COUPON);
    }
}
