package common.model.service;

import common.model.Destination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DestinationCollection {
    private final ArrayList<Destination> destinationList;

    public DestinationCollection() {
        destinationList = new ArrayList<>();
    }

    public List<Destination> getAllDestinations() {
        return Collections.unmodifiableList(destinationList);
    }

    public Destination saveDestination(Destination destination) {
        var optionalDestination = destinationList.stream().filter(existingFlight -> existingFlight.getUuid()
                .equals(destination.getUuid())).findFirst();
        if (optionalDestination.isEmpty()) {
            destination.setUuid(UUID.randomUUID());
            destinationList.add(destination);
            return destination;
        } else {
            var existingDestination = optionalDestination.get();
            existingDestination.setName(destination.getName());
            return existingDestination;
        }
    }

    public Destination getDestination(UUID uuid) {
        var optionalDestination = destinationList.stream().filter(existingFlight -> existingFlight.getUuid()
                .equals(uuid)).findFirst();
        if (optionalDestination.isEmpty()) {
            return null;
        }
        return optionalDestination.get();
    }
}
