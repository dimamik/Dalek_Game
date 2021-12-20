package controller;

import com.google.inject.Inject;
import model.Board;
import views.BoardView;

//    TODO To be used later - rn everything is handled in AppController
public final class BoardController {
    private final BoardView boardView;

    private final Board board;

    @Inject
    public BoardController(BoardView boardView, Board board) {
        this.boardView = boardView;
        this.board = board;
    }
}
