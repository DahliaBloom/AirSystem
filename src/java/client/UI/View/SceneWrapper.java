package client.UI.View;

import client.UI.Controllers.Controller;
import common.model.Notification;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class SceneWrapper {

    private static Stage stage;
    private Scene scene;

    SceneWrapper(String FXML_LOCATION) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource(FXML_LOCATION)));
            scene = new Scene(loader.load());
            scene.setUserData(loader);
            scene.setFill(Color.TRANSPARENT);
        } catch (IOException e) {
            System.err.println("File " + FXML_LOCATION + " not found.");
            System.exit(-1);
        }
    }

    Scene getScene() {
        return scene;
    }

    public static void setStage(Stage stage) {
        SceneWrapper.stage = stage;
    }

    public static void preloadScene(client.UI.View.Scene scene) {
        switch (scene) {
            case LOGIN -> LoginScene.get();
            case HOME -> HomeScene.get();
        }
    }

    public static void switchToScene(client.UI.View.Scene scene, boolean fadeAll) {
        Scene loadedScene = switch (scene) {
            case LOGIN -> LoginScene.get();
            case HOME -> HomeScene.get();
            case FOOD -> FoodScene.get();
            case SAFETY -> SafetyScene.get();
            case SEARCH -> SearchScene.get();
            case FEEDBACK -> FeedBackScene.get();
            default -> HomeScene.get(); // TODO
        };
        stage.setScene(loadedScene);
        Controller controller = ((FXMLLoader) loadedScene.getUserData()).getController();
        controller.setup(fadeAll);
        controller.fadeIn(fadeAll);
    }

    public static void switchToSceneWithFade(client.UI.View.Scene scene, Node node, client.UI.View.Scene currentScene) {
        FadeTransition transition = new FadeTransition(Duration.millis(600));
        transition.setOnFinished(evt -> switchToScene(scene, fadeTrans(currentScene, scene)));
        transition.setNode(node);
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.play();
    }

    private static boolean fadeTrans(client.UI.View.Scene currentScene, client.UI.View.Scene scene) {
        return currentScene == client.UI.View.Scene.LOGIN || scene == client.UI.View.Scene.LOGIN;
    }
}
