package client.UI.View;

import javafx.scene.Scene;

public class FoodScene extends SceneWrapper {
    private static final String FXML_LOCATION = "client/UI/FXML/Food.fxml";
    private static FoodScene foodScene;

    private FoodScene() {
        super(FXML_LOCATION);
    }

    public static Scene get() {
        if (foodScene == null) foodScene = new FoodScene();
        return foodScene.getScene();
    }

}
