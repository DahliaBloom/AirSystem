package server.rest;

import common.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.service.MainCollection;

import java.util.List;
import java.util.UUID;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class MainResource {


    private final MainCollection mainCollection;

    public static void main(String[] args) {
        SpringApplication.run(MainResource.class, args);
    }

    public MainResource(MainCollection collection){
        mainCollection = collection;
    }
    // Destinations
    @GetMapping("/destination")
    public ResponseEntity<List<Destination>> getAllDestinations(){
        return ResponseEntity.ok(mainCollection.getDestinationCollection().getAllDestinations());
    }
    @GetMapping("/Destination/get")
    public ResponseEntity<Destination> getDestination(@RequestParam(value = "dest", defaultValue = "null") UUID uuid) {
        return ResponseEntity.ok(mainCollection.getDestination(uuid));
    }
    // User

    @GetMapping("/User/get")
    public ResponseEntity<User> getUser(@RequestParam(value = "user", defaultValue = "null")UUID uuid){
        return ResponseEntity.ok(mainCollection.getUser(uuid));

    }
    @GetMapping("/User")
    public ResponseEntity<User> getLoggedIn(){
        return ResponseEntity.ok(new User("abc","adc"));
    }
    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestParam(value = "name", defaultValue = "null")String name,@RequestParam(value = "password", defaultValue = "null") String password){
        boolean created =  mainCollection.createUser(name,password);
        if(created){
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/logout")
    public ResponseEntity<User> logOut(){
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/User/Notif")
    public ResponseEntity<List<Notification>> getNotifications(){
        return ResponseEntity.ok(mainCollection.getUserCollection().checkForNotifications(mainCollection.getFlightCollection()));
    }

    @PostMapping("User/Login")
    public ResponseEntity<User> login(@RequestParam(value = "name", defaultValue = "null")String username, @RequestParam(value = "password", defaultValue = "null")String password){
        mainCollection.getUserCollection().login(username,password);
        return ResponseEntity.ok(null);
    }
    @PostMapping("User/addflight")
    public ResponseEntity<Flight> addBookedFlight(@RequestParam(value = "flight", defaultValue = "null")Flight flight){
        return ResponseEntity.ok(mainCollection.getUserCollection().addBookedFlight(flight));
    }
    @PostMapping("user/addreward")
    public ResponseEntity<Rewards> addReward(@RequestParam(value = "reward", defaultValue = "null")Rewards rewards){
        mainCollection.getUserCollection().addReward(rewards);
        return ResponseEntity.accepted().build();
    }
    @PostMapping("user/addtrip")
    public ResponseEntity<Trip> addTrip(@RequestParam(value = "trip", defaultValue = "null")Trip trip){
        mainCollection.addTrip(trip);
        return ResponseEntity.accepted().build();
    }
    @DeleteMapping("user/deleteFlight")
    public  ResponseEntity<Flight> removeBookedFlight(@RequestParam(value = "flight", defaultValue = "null")Flight flight){
        mainCollection.getUserCollection().removeBookedFlight(flight);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("user/deleteReward")
    public ResponseEntity<Rewards> removeReward(@RequestParam(value = "reward", defaultValue = "null")Rewards rewards){
        mainCollection.removeReward(rewards);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("user/deleteTrip")
    public ResponseEntity<Trip> removeTrip(@RequestParam(value = "trip", defaultValue = "null")Trip trip) {
        mainCollection.removeTrip(trip);
        return ResponseEntity.noContent().build();
    }

    // Flights

    @GetMapping("/flight/get")
    public ResponseEntity<List<Flight>> getAllFlights(){
        return ResponseEntity.ok(mainCollection.getFlightCollection().getFlights());
    }
    @GetMapping("/flight/getOne")
    public ResponseEntity<Flight> getAllFlight(@RequestParam(value = "flight", defaultValue = "null")UUID uuid){
        return ResponseEntity.ok(mainCollection.getFlightCollection().getFlight(uuid));
    }

    @PutMapping("/Flight/create")
    public ResponseEntity<Flight> createFlight(@RequestParam(value = "flight", defaultValue = "null")Flight flight){
        return ResponseEntity.ok(mainCollection.getFlightCollection().saveFlight(flight));
    }

    @PutMapping("/Flight/update")
    public ResponseEntity<Flight> updateFlight(@RequestParam(value = "flight", defaultValue = "null")Flight flight, UUID uuid){
        if(flight.getFlightID() != uuid){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(mainCollection.getFlightCollection().saveFlight(flight));
    }

    @DeleteMapping("/Flight/delete")
    public ResponseEntity<Void> deleteFight(@RequestParam(value = "uuid", defaultValue = "null")UUID uuid){
        mainCollection.getFlightCollection().deleteFlight(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("sayHello")
    public String sayHello() {
        return "Hello World!";
    }


}