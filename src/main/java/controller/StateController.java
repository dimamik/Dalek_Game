package controller;

import model.BoardCell;
import model.BoardObject;
import views.BoardCellView;
import views.BoardObjectView;
import views.BoardView;

import java.util.Optional;

public final class StateController {

    public static StateController instance;

    private final BoardView boardView;

    public StateController(BoardView boardView) {
        this.boardView = boardView;
        StateController.instance = this;
    }

    public static Optional<StateController> getInstance() {
        if (instance == null) {
            return Optional.empty();
        }
        return Optional.of(instance);
    }

    public void cellChangeOccurred(BoardCell boardCell) {
        BoardCellView boardCellView = boardView.getBoardCellView(boardCell.getPosition().getX(), boardCell.getPosition().getY());
        if (boardCell.getBoardObject().isPresent()) {
            BoardObject boardObject = boardCell.getBoardObject().get();
            boardCellView.drawBoardObjectView(new BoardObjectView(boardObject));
        } else {
            boardCellView.clearBoardObjectView();
        }
    }

    public void onCellChosen(int x, int y) {
        System.out.println("Cell chosen!" + x + y);
    }

}
