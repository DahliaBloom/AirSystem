package common.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Flight {
    private UUID flightID;
    private String name;
    private final String[] locationNames = new String[]{"Munich","London","Paris","Berlin", "Atlanta","Denver","Chicago","Singapore","Jakarta","Perth"};
    public UUID getFlightID() {
        return flightID;
    }

    public void setFlightID(UUID flightID) {
        this.flightID = flightID;
    }

    private int flightNumber;
    private Date departure;
    private Date arrival;
    private String gate;
    private int terminal;
    private int seat;
    private String airplaneType;
    private boolean isDelayed;
    private boolean isCancelled;
    private Destination destination;
    private final String airline;
    private String startingLocation;

    public String getAirline() {
        return airline;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public Flight(String destination_string) {
        flightID = UUID.randomUUID();
        isCancelled = false;
        isDelayed = false;
        name = new String[]{"J", "D", "R", "B", "K", "A", "C"}[(int) (Math.random() * 6)]+((int) (Math.random() * 1000))+new String[]{"f", "x", "z", "u", "p", "l", "i"}[(int) (Math.random() * 6)]+((int) (Math.random() * 10000));
        flightNumber = ((int) (Math.random() * 1000));
        destination = new Destination(destination_string);
        departure=generateRandomDateDaysAhead(Calendar.getInstance().getTime());
        arrival=generateRandomDate(departure);
        gate = new String[]{"A", "B", "C", "D", "E", "F", "G"}[(int) (Math.random() * 6)]+((int) (Math.random() * 4));
        terminal = randomInt(1,4);
        seat = randomInt(1, 124);
        airplaneType = new String[]{"Boeing 737-800", "Boeing 737-700", "Airbus A320", "Airbus A321", "Bombardier CRJ200", "Boeing 757-200", "Embraer E175", "Airbus A319", "Boeing 737-900ER", "Bombardier CRJ900"}[(int) (Math.random() * 10)];
        airline = locationNames[(int) (Math.random() * locationNames.length-1)];
        startingLocation = new String[]{"Munich","London","Paris","Rome"}[(int) (Math.random() * 4)];
    }
    public Flight() {
        flightID = UUID.randomUUID();
        isCancelled = false;
        isDelayed = false;
        name = new String[]{"J", "D", "R", "B", "K", "A", "C"}[(int) (Math.random() * 6)]+((int) (Math.random() * 1000))+new String[]{"f", "x", "z", "u", "p", "l", "i"}[(int) (Math.random() * 6)]+((int) (Math.random() * 10000));
        flightNumber = ((int) (Math.random() * 1000));
        departure=generateRandomDateDaysAhead(Calendar.getInstance().getTime());
        arrival=generateRandomDate(departure);
        gate = new String[]{"A", "B", "C", "D", "E", "F", "G"}[(int) (Math.random() * 6)]+((int) (Math.random() * 4));
        terminal = randomInt(1,4);
        seat = randomInt(1, 124);
        airplaneType = new String[]{"Boeing 737-800", "Boeing 737-700", "Airbus A320", "Airbus A321", "Bombardier CRJ200", "Boeing 757-200", "Embraer E175", "Airbus A319", "Boeing 737-900ER", "Bombardier CRJ900"}[(int) (Math.random() * 10)];
        airline = locationNames[(int) (Math.random() * locationNames.length-1)];
        startingLocation = new String[]{"Munich","London","Paris","Rome"}[(int) (Math.random() * 4)];
        destination = new Destination(new String[]{"Munich","London","Paris","Rome "}[(int) (Math.random() * 4)]);

    }

    public static Date generateRandomDate(Date min_Date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(min_Date);
        cal.add(Calendar.HOUR, randomInt(1,7));
        cal.add(Calendar.MINUTE, randomInt(7,55));
        return cal.getTime();
    }

    public static Date generateRandomDateDaysAhead(Date min_Date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(min_Date);
        cal.add(Calendar.DAY_OF_YEAR, randomInt(1,14));
        cal.add(Calendar.HOUR, randomInt(1,7));
        cal.add(Calendar.MINUTE, randomInt(7,55));
        return cal.getTime();
    }

    public static int randomInt(int from, int to){
        return from + (int)Math.round(Math.random() * (to - from));
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public boolean isDelayed() {
        return isDelayed;
    }

    public void setDelayed(boolean delayed) {
        isDelayed = delayed;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    @Override
    public String toString() {
        return "The Flight with the ID: " + flightID +
                ", called " + name + "|" + flightNumber +
                " departures at " + departure +
                " and arrives at " + arrival +
                " starts from Gate: " + gate +
                " and Terminal: " + terminal +
                ". You have the Seat No." + seat +
                " in the aircraft " + airplaneType + " flying with " + airline +
                ". Is It delayed: " + isDelayed +
                ". or even Cancelled: " + isCancelled ;
    }

}
