package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import model.Board;
import utils.CollisionHandler;
import utils.PositionUtil;

public class BoardOverviewController {

	private GameController gameController;
	private Board board;
	private CollisionHandler collisionHandler;
	private PositionUtil positionUtil;

	@FXML
	public Canvas canvas;

	@FXML
	public VBox rightSide;

	@FXML
	public VBox movementButtons;

//	public void setupGame() {
//		this.board = new Board(200, 200);
//		this.collisionHandler = new CollisionHandler(board);
//		this.positionUtil = new PositionUtil(board, collisionHandler);
//	}

	@FXML
	private void initialize() {
		//TODO
	}

	public void onPress(ActionEvent actionEvent) {
		System.out.println("Button pressed!");
	}

	public void setAppController(GameController gameController) {
		this.gameController = gameController;
	}
}
