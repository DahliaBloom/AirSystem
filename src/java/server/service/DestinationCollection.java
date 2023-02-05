package server.service;

import common.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DestinationCollection {
    private final ArrayList<Destination> destinationList;

    public DestinationCollection(){
        destinationList = new ArrayList<>();
    }
    //returns a List with all the Destinations
    public List getAllDestinations(){
        return Collections.unmodifiableList(destinationList);
    }
    //save a destination to the collection
    public Destination saveDestination(Destination destination){
        var optionalDestination =destinationList.stream().filter(existingFlight -> existingFlight.getUuid()
                .equals(destination.getUuid())).findFirst();
        if (optionalDestination.isEmpty()){
            destination.setUuid(UUID.randomUUID());
            destinationList.add(destination);
            return destination;
        }
        else{
            var existingDestination =optionalDestination.get();
            existingDestination.setName(destination.getName());
            return existingDestination;
        }
    }
    //get a destination from the collection
    public Destination getDestination(UUID uuid){
        var optionalDestination =destinationList.stream().filter(existingFlight -> existingFlight.getUuid()
                .equals(uuid)).findFirst();
        if(optionalDestination.isEmpty()){
            return null;
        }
        return optionalDestination.get();
    }
}
