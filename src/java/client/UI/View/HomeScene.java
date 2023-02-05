package client.UI.View;

import javafx.scene.Scene;

class HomeScene extends SceneWrapper {

    private static final String FXML_LOCATION = "client/UI/FXML/Home.fxml";
    private static HomeScene homeScene;

    private HomeScene() {
        super(FXML_LOCATION);
    }

    public static Scene get() {
        if (homeScene == null) homeScene = new HomeScene();
        return homeScene.getScene();
    }
}
