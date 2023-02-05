package client.UI.Controllers;

import client.UI.View.NotificationScene;
import client.UI.View.Scene;
import client.UI.View.SceneWrapper;
import common.model.Notification;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class Controller {
    @FXML
    protected AnchorPane anchorPane;
    @FXML
    protected Pane fadePane;
    @FXML
    protected Pane minFadePane;
    @FXML
    protected Label messagePane;

    public void fadeIn(boolean fadeAll) {
        FadeTransition transition = new FadeTransition(Duration.millis(600));
        transition.setNode(fadeAll ? fadePane : minFadePane);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.play();
    }

    public abstract void setup(boolean fadeAll);

    protected abstract void stopTimers();

    @FXML
    protected void clickOnExitButton() {
        StageController.getStageController().closeApplication();
    }

    @FXML
    protected void setOffsetForWindow(MouseEvent e) {
        StageController.getStageController().setOffset(anchorPane, e);
    }

    @FXML
    protected void moveWindow(MouseEvent e) {
        StageController.getStageController().moveWindow(anchorPane, e);
    }

    @FXML
    protected void clickOnMinimizeButton() {
        StageController.getStageController().minimizeApplication(anchorPane);
    }

    @FXML
    protected void logout() {
        stopTimers();
        SceneWrapper.switchToSceneWithFade(Scene.LOGIN, fadePane, Scene.HOME);
    }

    @FXML
    protected void home() {
        stopTimers();
        SceneWrapper.switchToSceneWithFade(Scene.HOME, minFadePane, Scene.HOME);
    }

    @FXML
    protected void food() {
        stopTimers();
        SceneWrapper.switchToSceneWithFade(Scene.FOOD, minFadePane, Scene.HOME);
    }

    @FXML
    protected void safety() {
        stopTimers();
        SceneWrapper.switchToSceneWithFade(Scene.SAFETY, minFadePane, Scene.HOME);
    }

    @FXML
    protected void notification() {
        stopTimers();
        NotificationScene.show(anchorPane);
    }

    @FXML
    protected void feedback() {
        stopTimers();
        SceneWrapper.switchToSceneWithFade(Scene.FEEDBACK, minFadePane, Scene.HOME);
    }

    @FXML
    protected void search() {
        stopTimers();
        SceneWrapper.switchToSceneWithFade(Scene.SEARCH, minFadePane, Scene.HOME);
    }

    @FXML
    protected void favourites() {
        stopTimers();
        SceneWrapper.switchToSceneWithFade(Scene.FAVOURITES, minFadePane, Scene.HOME);
    }

    @FXML
    protected void booked() {
        stopTimers();
        SceneWrapper.switchToSceneWithFade(Scene.BOOKED, minFadePane, Scene.HOME);
    }

    @FXML
    protected void onLogoutIconEntered() {
        showMessagePane("Logout");
    }

    @FXML
    protected void onHomeIconEntered() {
        showMessagePane("Home");
    }

    @FXML
    protected void onFoodIconEntered() {
        showMessagePane("Restaurant");
    }

    @FXML
    protected void onBookedIconEntered() {
        showMessagePane("Booked");
    }

    @FXML
    protected void onSearchIconEntered() {
        showMessagePane("Search");
    }

    @FXML
    protected void onSafetyIconEntered() {
        showMessagePane("Safety");
    }

    @FXML
    protected void onNotificationIconEntered() {
        showMessagePane("Get Help");
    }

    @FXML
    protected void onFeedbackIconEntered() {
        showMessagePane("Feedback");
    }

    @FXML
    protected void onFavouritesIconEntered() {
        showMessagePane("Favourites");
    }

    private boolean messageActive = false;
    private boolean fade = true;

    @FXML
    protected void showMessagePane(String message) {
        messagePane.setText(message);
        messageActive = true;
        if (fade) {
            FadeTransition transition = new FadeTransition(Duration.millis(600));
            transition.setOnFinished(evt -> hideMessagePane());
            transition.setNode(messagePane);
            transition.setFromValue(0);
            transition.setToValue(1);
            transition.play();
            fade = false;
        }
    }

    @FXML
    protected void hideMessagePane() {
        FadeTransition transition = new FadeTransition(Duration.millis(600));
        transition.setNode(messagePane);
        transition.setFromValue(1);
        transition.setToValue(0);
        new Thread(() -> {
            int counter = 300;
            do {
                if (messageActive) counter = 300;
                messageActive = false;
                counter--;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ignored) {
                }
            } while (counter > 0);
            fade = true;
            transition.play();
        }).start();
    }

    @FXML
    protected void openPopup(Pane popup) {
        popup.toFront();
        popup.setOpacity(0);
        FadeTransition transition = new FadeTransition(Duration.millis(700));
        transition.setNode(popup);
        popup.setVisible(true);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.play();
    }

    @FXML
    protected void closePopup(Pane popup) {
        FadeTransition transition = new FadeTransition(Duration.millis(700));
        transition.setNode(popup);
        transition.setOnFinished(evt -> {
            popup.setVisible(false);
            popup.toBack();
        });
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.play();
    }

}
