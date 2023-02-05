package client.UI.View;

import javafx.scene.Scene;

class SafetyScene extends SceneWrapper {

    private static final String FXML_LOCATION = "client/UI/FXML/Safety.fxml";
    private static SafetyScene safetyScene;

    private SafetyScene() {
        super(FXML_LOCATION);
    }

    public static Scene get() {
        if (safetyScene == null) safetyScene = new SafetyScene();
        return safetyScene.getScene();
    }
}
