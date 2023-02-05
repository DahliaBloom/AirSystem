package client.UI.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SearchItemController {

    @FXML
    Button bookButton;

    @FXML
    protected void setBooked() {
        // TODO call to server with booked flight
        bookButton.setId("bookedButton");
        bookButton.setText("Booked");
    }

}
