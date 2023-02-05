package client.UI.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class RequestHelpController extends Controller {


    private Timer clockUpdater;

    @Override
    public void setup(boolean fadeAll) {
        fadePane.setOpacity(fadeAll ? 0 : 1);
        minFadePane.setOpacity(fadeAll ? 1 : 0);
    }

    @Override
    protected void stopTimers() {
        clockUpdater.cancel();
    }

    @FXML
    public void requestServiceOkay() {
        anchorPane.setVisible(false);
    }
}
