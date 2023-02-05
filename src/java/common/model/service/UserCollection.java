package common.model.service;

import common.model.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserCollection {
    private final ArrayList<User> userList;
    private User loggedIn;


    public UserCollection() {
        userList = new ArrayList<User>();
    }

    public boolean createUser(String username, String password) {
        if (findUser(UUID.nameUUIDFromBytes(username.getBytes(StandardCharsets.UTF_8))) != null) {
            return false;
        }
        userList.add(new User(username, password));
        return true;
    }

    public User findUser(UUID id) {
        var optionalUser = userList.stream().filter(existingUser -> existingUser.getUuid()
                .equals(id)).findFirst();
        if (!optionalUser.isEmpty()) {
            return optionalUser.get();
        }
        return null;
    }

    public boolean login(String username, String password) {
        User user = findUser(UUID.nameUUIDFromBytes(username.getBytes(StandardCharsets.UTF_8)));
        if (user.getPassword().equals(password)) {
            loggedIn = user;
            return true;
        }
        return false;
    }

    public boolean logOut() {
        if (loggedIn != null) {
            loggedIn = null;
            return true;
        }
        return false;
    }

    public List<Notification> checkForNotifications(FlightCollection flightCollection) {
        for (int i = 0; i < loggedIn.getBookedFlights().size(); i++) {
            Flight userFlight = loggedIn.getBookedFlights().get(i);
            Flight databankFlight = flightCollection.getFlight(userFlight.getFlightID());
            if (databankFlight.isCancelled()) {
                userFlight.setCancelled(true);
                loggedIn.addNotification(new Notification(databankFlight, NotificationType.CANCELLED));
                break;
            } else if (databankFlight.isDelayed()) {
                userFlight.setDelayed(true);
                loggedIn.addNotification(new Notification(databankFlight, NotificationType.DELAYED));
            }
        }
        return loggedIn.getNotifications();
    }

    public Notification clearNotification(Notification notification) {
        loggedIn.removeNotification(notification);
        return notification;
    }

    public List<Flight> getBookedFlights() {
        return loggedIn.getBookedFlights();
    }



    public List<Trip> getBookmarkedTrips() {
        return loggedIn.getBookmarkedTrips();
    }

    public List<Rewards> getRewards() {
        return loggedIn.getRewards();
    }

    public Flight addBookedFlight(Flight flight) {
        return loggedIn.saveBookedFlight(flight);
    }



    public boolean addReward(Rewards rewards) {
        return loggedIn.addReward(rewards);
    }

    public boolean addTrip(Trip trip) {
        return loggedIn.addTrip(trip);
    }

    public boolean removeBookedFlight(Flight flight) {
        return loggedIn.removeBookedFlight(flight.getFlightID());
    }



    public boolean removeReward(Rewards rewards) {
        return loggedIn.removeReward(rewards);
    }

    public boolean removeTrip(Trip trip) {
        return loggedIn.removeTrip(trip);
    }


}
