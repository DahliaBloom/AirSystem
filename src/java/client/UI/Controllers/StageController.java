package client.UI.Controllers;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StageController {

    private static StageController stageController;

    private double xOffset, yOffset;

    private StageController() {
    }

    protected void closeApplication() {
        System.exit(0);
    }

    protected void setOffset(Node node, MouseEvent e) {
        xOffset = node.getScene().getWindow().getX() - e.getScreenX();
        yOffset = node.getScene().getWindow().getY() - e.getScreenY();
    }

    protected void moveWindow(Node node, MouseEvent e) {
        node.getScene().getWindow().setX(e.getScreenX() + xOffset);
        node.getScene().getWindow().setY(e.getScreenY() + yOffset);
    }

    protected void minimizeApplication(Node node) {
        ((Stage) node.getScene().getWindow()).setIconified(true);
    }

    protected static StageController getStageController() {
        if (stageController == null) {
            stageController = new StageController();
        }
        return stageController;
    }
}