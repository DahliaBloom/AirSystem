package client.UI.Controllers;

import client.LoginValidation.LoginValidation;
import client.UI.View.Scene;
import client.UI.View.SceneWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class LoginController extends Controller {
    private static final String BG_FOR_INVALIDE_DATA = "-fx-background-color: #FFD9D9, #FFD9D9, #ff5071, #FFD9D9;";
    private static final String BG_FOR_VALIDE_DATA = "-fx-background-color: white, white, #ff5071, white;";
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordFieldClearText;
    @FXML
    private ImageView eyeShutter;

    @Override
    public void setup(boolean fadeAll) {
        minFadePane.setOpacity(1);
        fadePane.setOpacity(0);
        usernameField.clear();
        passwordField.clear();
        usernameField.requestFocus();
    }

    @Override
    protected void stopTimers() {
    }

    @FXML
    protected void showPassword() {
        passwordFieldClearText.setText(passwordField.getText());
        passwordFieldClearText.setStyle(passwordField.getStyle());
        passwordField.setEditable(false);
        eyeShutter.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("client/UI/img/eye.png"))));
        passwordFieldClearText.setVisible(true);
    }

    @FXML
    protected void hidePassword() {
        eyeShutter.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("client/UI/img/eyeClosed.png"))));
        passwordFieldClearText.setVisible(false);
        passwordField.setEditable(true);
    }

    @FXML
    protected void onSubmit() {
        if (usernameField.getText().isBlank()) {
            usernameField.requestFocus();
            return;
        } else if (passwordField.getText().isBlank()) {
            passwordField.requestFocus();
            return;
        }
        switch (LoginValidation.validate(usernameField.getText(), passwordField.getText())) {
            case SUCCESSFUL -> {
                usernameField.setStyle(BG_FOR_VALIDE_DATA);
                passwordField.setStyle(BG_FOR_VALIDE_DATA);
                SceneWrapper.switchToSceneWithFade(Scene.HOME, fadePane, Scene.LOGIN);
            }
            case WRONG_USERNAME -> {
                showMessagePane("Incorrect username");
                usernameField.setStyle(BG_FOR_INVALIDE_DATA);
                passwordField.setStyle(BG_FOR_VALIDE_DATA);
                usernameField.requestFocus();
            }
            case WRONG_PASSWORD -> {
                showMessagePane("Incorrect password");
                passwordField.setStyle(BG_FOR_INVALIDE_DATA);
                usernameField.setStyle(BG_FOR_VALIDE_DATA);
                passwordField.requestFocus();
            }
        }
    }
}
