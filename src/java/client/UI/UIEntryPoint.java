package client.UI;

import client.UI.View.Scene;
import client.UI.View.SceneWrapper;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class UIEntryPoint extends Application {

    @Override
    public void start(Stage stage) {
        loadFonts();
        SceneWrapper.setStage(stage);
        preloadScenes();
        SceneWrapper.switchToScene(Scene.LOGIN, true);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Air System");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("client/UI/img/planeIcon.png"))));
        stage.show();
    }

    private void preloadScenes() {
        SceneWrapper.preloadScene(Scene.HOME);
        SceneWrapper.preloadScene(Scene.FOOD);
        SceneWrapper.preloadScene(Scene.SAFETY);
    }

    private void loadFonts() {
        Font.loadFont(getClass().getClassLoader().getResourceAsStream("client/UI/font/VisbyRound.otf"), 20);
    }
}
