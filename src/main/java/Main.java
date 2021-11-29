import javafx.application.Application;
import javafx.stage.Stage;
import controller.AppController;

public class Main extends Application {
	private Stage primaryStage;
	private AppController gameController;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Board Game");

		this.gameController = new AppController(primaryStage);
		this.gameController.initRootLayout();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
