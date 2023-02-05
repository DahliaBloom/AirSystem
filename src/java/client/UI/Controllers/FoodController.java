package client.UI.Controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class FoodController extends Controller {

    @FXML
    protected Pane popup;

    @Override
    public void setup(boolean fadeAll) {
        fadePane.setOpacity(fadeAll ? 0 : 1);
        minFadePane.setOpacity(fadeAll ? 1 : 0);
    }

    @Override
    protected void stopTimers() {
    }

    @FXML
    protected void foodOkay() {
        closePopup(popup);
    }

    @FXML
    protected void orderFood1() {
        openPopup(popup);
    }

    @FXML
    protected void orderFood2() {
        orderFood1();
    }

    @FXML
    protected void orderFood3() {
        orderFood1();
    }

    @FXML
    protected void orderFood4() {
        orderFood1();
    }
}
