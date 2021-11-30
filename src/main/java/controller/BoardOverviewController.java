package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import views.BoardView;

public class BoardOverviewController {


	private AppController appController;


	@FXML
	public VBox rightSide;

	@FXML
	public VBox movementButtons;

	@FXML
	public BoardView boardView;

	@FXML
	private void initialize() {
		//TODO Tu łączymy view i controllers
		for(int i=0; i<10; i++){

		}
//		boardView.x
//		boardView.fillProperty().
//
//		);
	}

	public void onPress(ActionEvent actionEvent) {
		System.out.println("Button pressed!" +  actionEvent);
	}

	public void setAppController(AppController gameController) {
		this.appController = gameController;
	}

	private void bindBoardCellViewWithModel(){
//		TODO Tu łączymy BoardCellView z BoardCell
//		W taki sposób, że rejestrujemy zmianę w liście boardObjects
//		I jak się zmieni to odpowiednio rerendujemy BoardView
//		Tylko ta klasa ma instancę BoardObject, nie przekazujemy dalej
	}


}
