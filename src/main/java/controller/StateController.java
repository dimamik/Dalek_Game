package controller;

import javafx.scene.paint.Color;
import model.BoardCell;
import views.BoardCellView;
import views.BoardObjectView;
import views.BoardView;
import java.util.Optional;

public final class StateController {

    public static StateController instance;

    private final BoardView boardView;

    public static Optional<StateController> getInstance() {
        if (instance == null) {
            return Optional.empty();
        }
        return Optional.of(instance);
    }

    public StateController(BoardView boardView) {
        this.boardView = boardView;
        StateController.instance = this;
    }

    public void cellChangeOccurred(BoardCell boardCell) {
        BoardCellView boardCellView = boardView.getBoardCellView(boardCell.getPosition().getX(), boardCell.getPosition().getY());
        boardCellView.drawBoardObjectView(new BoardObjectView(Color.BLACK));
    }

    public void onCellChosen(int x, int y) {
        System.out.println("Cell chosen!" + x + y);
    }

}
