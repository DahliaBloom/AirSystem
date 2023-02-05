package client.UI.Controllers;

import client.FlightData.FlightData;
import client.FlightSearch.FlightResult;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Objects;

public class SearchController extends Controller {
    @FXML
    private VBox resultBox;
    @FXML
    private ChoiceBox<String> fromBox;
    @FXML
    private ChoiceBox<String> toBox;

    @Override
    public void setup(boolean fadeAll) {
        fadePane.setOpacity(fadeAll ? 0 : 1);
        minFadePane.setOpacity(fadeAll ? 1 : 0);

        String[] locations = {"Munich", "New York City", "Rome", "Berlin", "Paris"};
        for (String loc : locations) {
            toBox.getItems().add(loc);
            fromBox.getItems().add(loc);
        }

        toBox.getSelectionModel().selectedItemProperty().addListener(this::onChoiceBoxChange);
        fromBox.getSelectionModel().selectedItemProperty().addListener(this::onChoiceBoxChange);
    }


    private void onChoiceBoxChange (ObservableValue<? extends String> ov, String old_val, String new_val) {
        if (!old_val.equals(new_val) && !fromBox.getValue().equals("From") && !toBox.getValue().equals("To")) {
            resultBox.getChildren().clear();
            for (FlightData flight : FlightResult.searchResults(fromBox.getValue(), toBox.getValue())) {
                AnchorPane item = getSearchItem();
                resultBox.getChildren().add(item);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM/HH:mm").withZone(ZoneId.systemDefault());

                try {
                    ((Text) item.lookup("#departure")).setText("Departure: " + formatter.format(Instant.parse(flight.startTime)));
                } catch (DateTimeException e) {
                    ((Text) item.lookup("#departure")).setText("Departure: - ");
                }
                try {
                    ((Text) item.lookup("#arrival")).setText("Arrival:       " + formatter.format(Instant.parse(flight.arrivalTime)));
                } catch (DateTimeException e) {
                    ((Text) item.lookup("#arrival")).setText("Arrival:        - ");
                }
                ((Text) item.lookup("#gate")).setText("Gate:       " + flight.gate);
                ((Text) item.lookup("#terminal")).setText("Terminal: " + flight.terminal);
            }
        }
    }


    private AnchorPane getSearchItem() {
        try {
            return (new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("client/UI/FXML/SearchItem.fxml")))).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void stopTimers() {
        toBox.getItems().clear();
        fromBox.getItems().clear();
    }
}