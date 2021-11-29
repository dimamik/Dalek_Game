package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class BoardView extends Rectangle {

    public BoardView(int width, int height) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myComponent.fxml"));
    }
}
