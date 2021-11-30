import controller.AppController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        AppController gameController = new AppController(primaryStage);
        gameController.initRootLayout();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
