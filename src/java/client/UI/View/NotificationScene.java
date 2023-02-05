package client.UI.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NotificationScene extends SceneWrapper {

    private static final String FXML_LOCATION = "client/UI/FXML/GetHelp.fxml";
    private static NotificationScene homeScene;

    private NotificationScene() {
        super(FXML_LOCATION);
    }

    public static Stage show(AnchorPane pane) {
        Parent root;
        try {
//            root = new FXMLLoader(Objects.requireNonNull(NotificationScene.class.getClassLoader().getResource(FXML_LOCATION)));
            root = FXMLLoader.load(NotificationScene.class.getClassLoader().getResource(FXML_LOCATION));
            Stage stage = new Stage();
            stage.setTitle("Get Help");
            Scene scene = new Scene(root, 600, 200);

            //TODO remove whitespace around popup, add functionality to okay-button
            stage.setScene(scene);

            stage.show();
            return stage;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
