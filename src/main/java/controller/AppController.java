package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.BoardCell;
import utils.GameUtil;
import views.BoardView;

/**
 * Is responsible for Controlling Board with its objects and movement keys
 * Later we will need to split this up into board controller and menu controller
 */
public class AppController {
    public GameUtil gameUtil;

    @FXML
    public VBox rightSide;

    @FXML
    public VBox movementButtons;

    @FXML
    public BoardView boardView;

    @FXML
    private void initialize() {

// TODO There Model And View should be connected using one/two way bindings and event listeners
        connectViewAndModel();
    }

    private void connectViewAndModel() {
    }

    public void onPress(ActionEvent actionEvent) {
        System.out.println("Button pressed!" + actionEvent);
        controlModel(actionEvent);
    }

    private void controlModel(ActionEvent actionEvent) {
// TODO There we need to control the model beh based on the action type

    }

    //    This can be moved to its own Cell Controller
    public void cellChangeOccured(BoardCell boardCell) {
//        TODO implement CellView rerendering knowing the boardCell

    }

    private void bindBoardCellViewWithModel() {
//		TODO Tu łączymy BoardCellView z BoardCell
//		W taki sposób, że rejestrujemy zmianę w liście boardObjects
//		I jak się zmieni to odpowiednio rerendujemy BoardView
//		Tylko ta klasa ma instancę BoardObject, nie przekazujemy dalej
    }


    public void prepareGame() {
//        TODO There GameUtils should be started and controlled
//        Can be passed upper to AppController if needed
        new GameUtil();
//        TODO Setup setters to feed controller with needed data
//        Such as: board,
    }
}
