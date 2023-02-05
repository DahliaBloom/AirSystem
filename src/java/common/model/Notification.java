package common.model;

public class Notification {
    private final Flight flight;
    private final NotificationType notificationType;

    public Notification(Flight flight, NotificationType notificationType) {
        this.flight = flight;
        this.notificationType = notificationType;
    }

    public String toString() {
        if (notificationType == NotificationType.CANCELLED) {
            return "Flight " + flight + " was cancelled.";
        } else return "Flight " + flight + " was delayed.";
    }
    public String getType(){
        return notificationType.name();
    }
    public String getFlightName(){
        return flight.getName();
    }
}
