package client.UI.Controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGenerator;

public class FeedBackController extends Controller {


    @FXML
    protected RadioButton c1, c2, c3, c4, c5;

    @FXML
    protected RadioButton e1, e2, e3, e4, e5;

    @FXML
    protected RadioButton f1, f2, f3, f4, f5;

    @FXML
    protected TextArea commentSection;

    @FXML
    protected Text couponCode;

    protected ArrayList<RadioButton> radioButtons = new ArrayList<>();

    @FXML
    protected Pane popup;


    @Override
    public void setup(boolean fadeAll) {
        fadePane.setOpacity(fadeAll ? 0 : 1);
        minFadePane.setOpacity(fadeAll ? 1 : 0);
        radioButtons.addAll(Arrays.asList(c1, c2, c3, c4, c5, e1, e2, e3, e4, e5, f1, f2, f3, f4, f5));
    }

    @Override
    protected void stopTimers() {
    }

    @FXML
    protected void feedBackThanks() {
        closePopup(popup);
    }

    @FXML
    protected void sendFeedback() {
        commentSection.clear();
        radioButtons.addAll(Arrays.asList(c1, c2, c3, c4, c5, e1, e2, e3, e4, e5, f1, f2, f3, f4, f5));
        int max = 939999;
        int min = 120392;
        int code = (int) Math.floor(Math.random()*(max-min+1)+min);
        couponCode.setText("#" + code);
        openPopup(popup);
        for (RadioButton rb : radioButtons) {
            rb.setSelected(false);
        }
        commentSection.clear();
    }
}
