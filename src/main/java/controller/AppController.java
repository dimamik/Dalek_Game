package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppController {

    private Stage primaryStage;

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

            // set initial data into controller
            BoardOverviewController controller = loader.getController();
            controller.setAppController(this);
//            controller.setupGame();

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
