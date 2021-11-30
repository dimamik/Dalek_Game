package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.GameUtil;

import java.io.IOException;

/**
 * Responsible for startup and initial primary stage setup
 */
public class AppController {

    private final Stage primaryStage;

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initRootLayout() {
        try {
            this.primaryStage.setTitle("Board Game");

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppController.class.getResource("../view/AppView.fxml"));
            BorderPane rootLayout = loader.load();

            // set up BoardOverviewController
            BoardOverviewController controller = loader.getController();
            controller.setAppController(this);

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            // don't do this in common apps
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
