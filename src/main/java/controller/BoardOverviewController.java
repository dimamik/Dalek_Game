package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;

public class BoardOverviewController {

	private AppController gameController;


	@FXML
	public VBox rightSide;

	@FXML
	public VBox movementButtons;

	@FXML
	private void initialize() {
		//TODO Tu łączymy view i controllers
		System.out.println("Hello from initialize");
	}

	public void onPress(ActionEvent actionEvent) {
		System.out.println("Button pressed!");
	}

	public void setAppController(AppController gameController) {
		this.gameController = gameController;
	}
}
