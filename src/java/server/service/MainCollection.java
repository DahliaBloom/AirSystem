package server.service;

import common.model.*;
import common.model.service.DestinationCollection;
import common.model.service.FlightCollection;
import common.model.service.UserCollection;

import java.util.List;
import java.util.UUID;

public class MainCollection {
    private DestinationCollection destinationCollection;
    private FlightCollection flightCollection;
    private UserCollection userCollection;

    public MainCollection(){
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

    //Get or Safe stuff
    public Flight getFlight(UUID uuid){
        return flightCollection.getFlight(uuid);
    }
    public Destination getDestination(UUID uuid){
        return destinationCollection.getDestination(uuid);
    }
    public User getUser(UUID uuid){
        return userCollection.findUser(uuid);
    }
    public Flight saveFlight(Flight flight){
        return flightCollection.saveFlight(flight);
    }
    public Destination saveDestination(Destination destination){
        return destinationCollection.saveDestination(destination);
    }
    public boolean createUser(String name, String password){
        return userCollection.createUser(name, password);
    }
    // User stuff below
    public User findUser(UUID uuid){
        return userCollection.findUser(uuid);
    }
    public boolean login(String username, String password){
        return userCollection.login(username,password);
    }
    public boolean logOut(){
        return userCollection.logOut();
    }
    public List<Notification> checkForNotifications(){
        return userCollection.checkForNotifications(flightCollection);
    }
    public Notification clearNotification(Notification notification){
        return clearNotification(notification);
    }
    // User getter and setter
    public List<Flight> getBookedFlights(){
        return userCollection.getBookedFlights();
    }
    public List<Trip> getBookmarkedTrips(){
        return userCollection.getBookmarkedTrips();
    }
    public List<Rewards> getRewards(){
        return userCollection.getRewards();
    }
    public Flight addBookedFlight(Flight flight){
        return userCollection.addBookedFlight(flight);
    }
    public boolean addReward(Rewards rewards){
        return userCollection.addReward(rewards);
    }
    public boolean addTrip(Trip trip){
        return userCollection.addTrip(trip);
    }
    public boolean removeBookedFlight(Flight flight){
        return userCollection.removeBookedFlight(flight);
    }
    public boolean removeReward(Rewards rewards){
        return userCollection.removeReward(rewards);
    }
    public boolean removeTrip(Trip trip){
        return userCollection.removeTrip(trip);
    }

    // for testing only
    public void setup(){
        for (int i = 0; i < 10; i++){
            Flight temp = new Flight();
            saveFlight(temp);
        }
        createUser("Jan","abc");
        login("Jan","abc");
    }

}
