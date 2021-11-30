import controller.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Board Game");

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppController.class.getResource("../view/AppView.fxml"));
            BorderPane rootLayout = loader.load();

            // set up AppController
            AppController controller = loader.getController();
            controller.prepareGame();

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }
    }
}
