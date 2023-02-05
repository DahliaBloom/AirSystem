package client.UI.View;

import javafx.scene.Scene;

public class FeedBackScene extends SceneWrapper {
    private static final String FXML_LOCATION = "client/UI/FXML/Feedback.fxml";
    private static FeedBackScene feedBackScene;

    private FeedBackScene() {
        super(FXML_LOCATION);
    }

    public static Scene get() {
        if (feedBackScene == null) feedBackScene = new FeedBackScene();
        return feedBackScene.getScene();
    }

}
