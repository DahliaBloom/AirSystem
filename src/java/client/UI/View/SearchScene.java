package client.UI.View;

import javafx.scene.Scene;

class SearchScene extends SceneWrapper {

    private static final String FXML_LOCATION = "client/UI/FXML/Search.fxml";
    private static SearchScene searchScene;

    private SearchScene() {
        super(FXML_LOCATION);
    }

    public static Scene get() {
        if (searchScene == null) searchScene = new SearchScene();
        return searchScene.getScene();
    }
}
