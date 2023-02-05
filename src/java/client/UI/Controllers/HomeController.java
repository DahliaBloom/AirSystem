package client.UI.Controllers;

import client.FlightData.FlightData;
import client.Location;
import common.util.WorldMap;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HomeController extends Controller {
    @FXML
    private Label clock;
    @FXML
    private Label date;
    @FXML
    private Label destination;
    @FXML
    private ImageView weatherIcon;
    @FXML
    private Label weatherState;
    @FXML
    private Label degrees;
    @FXML
    private Label startTime;
    @FXML
    private Label arrivalTime;
    @FXML
    private Label seat;
    @FXML
    private Label terminal;
    @FXML
    private Label gate;
    @FXML
    private StackPane mapPane;

    private Timer clockUpdater;

    @Override
    public void setup(boolean fadeAll) {
        fadePane.setOpacity(fadeAll ? 0 : 1);
        minFadePane.setOpacity(fadeAll ? 1 : 0);
        WorldMap.setTravelroute(Location.Munich, Location.New_York_City);
        mapPane.getChildren().add(new WorldMap().getMap());

        updateClock();
        clockUpdater = new Timer();
        clockUpdater.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateClock();
            }
        }, 0L, 1000);
        getFlightData();
    }

    private void getFlightData() {
        FlightData data = FlightData.getCurrentFlight();
        assert data != null;
        destination.setText("Dest: " + data.destination);
        switch (data.weather.weatherState) {
            case Sunny -> {
                weatherIcon.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("client/UI/img/weather/sun.png"))));
                weatherState.setText("Sunny");
            }
            case Cloudy -> {
                weatherIcon.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("client/UI/img/weather/clouds.png"))));
                weatherState.setText("Cloudy");
            }
            case Covered -> {
                weatherIcon.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("client/UI/img/weather/covered.png"))));
                weatherState.setText("Covered");
            }
            case Rainy -> {
                weatherIcon.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("client/UI/img/weather/rain.png"))));
                weatherState.setText("Rainy");
            }
            case Storm -> {
                weatherIcon.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("client/UI/img/weather/storm.png"))));
                weatherState.setText("Storm");
            }
            case SunAndRain -> {
                weatherIcon.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("client/UI/img/weather/sun+rain.png"))));
                weatherState.setText("Mixed");
            }
        }
        degrees.setText("  " + data.weather.degrees + " °C");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.systemDefault());
        try {
            startTime.setText("Start: " + formatter.format(Instant.parse(data.startTime)));
        } catch (Exception e) {
            startTime.setText("Start: - ");
        }
        try {
            arrivalTime.setText("Arrival: " + formatter.format(Instant.parse(data.arrivalTime)));
        } catch (Exception e) {
            arrivalTime.setText("Arrival: - ");
        }
        seat.setText("#" + data.seat);
        terminal.setText("#" + data.terminal);
        gate.setText("#" + data.gate);
    }

    @Override
    protected void stopTimers() {
        clockUpdater.cancel();
    }

    private void updateClock() {
        Platform.runLater(() -> {
            LocalDateTime time = LocalDateTime.now();
            String hour = String.valueOf(time.getHour());
            if (hour.length() == 1) hour = "0" + hour;
            String minutes = String.valueOf(time.getMinute());
            if (minutes.length() == 1) minutes = "0" + minutes;
            clock.setText(hour + ":" + minutes);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd.MM.yyyy");
            date.setText(time.format(formatter));
        });
    }
}
