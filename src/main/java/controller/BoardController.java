package controller;

import model.BoardCell;
import model.BoardObject;
import views.BoardCellView;
import views.BoardObjectView;
import views.BoardView;

import java.util.Optional;

public final class BoardController {

    public static BoardController instance;

    private final BoardView boardView;

    public BoardController(BoardView boardView) {
        this.boardView = boardView;
        BoardController.instance = this;
    }

    public static Optional<BoardController> getInstance() {
        if (instance == null) {
            return Optional.empty();
        }
        return Optional.of(instance);
    }

    public void cellChangeOccurred(BoardCell boardCell) {
        BoardCellView boardCellView = boardView.getBoardCellView(boardCell.getPosition().x(), boardCell.getPosition().y());
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
