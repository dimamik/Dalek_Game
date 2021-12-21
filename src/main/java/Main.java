import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.GameModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import service.FxmlLoaderService;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        final Injector injector = Guice.createInjector(new GameModule());

        final FxmlLoaderService fxmlLoaderService = injector.getInstance(FxmlLoaderService.class);

        try {
            primaryStage.setTitle("Board Game");

//            load layout from FXML file
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(AppController.class.getResource("../view/AppView.fxml"));

            FXMLLoader loader = fxmlLoaderService.getLoader(getClass().getResource("/view/AppView.fxml"));
            BorderPane rootLayout = loader.load();

//            set up AppController
//            AppController controller = loader.getController();

//            add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
