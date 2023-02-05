package client.UI.View;

import javafx.scene.Scene;

class LoginScene extends SceneWrapper {

    private static final String FXML_LOCATION = "client/UI/FXML/Login.fxml";
    private static LoginScene loginScene;

    private LoginScene() {
        super(FXML_LOCATION);
    }

    public static Scene get() {
        if (loginScene == null) loginScene = new LoginScene();
        return loginScene.getScene();
    }
}
