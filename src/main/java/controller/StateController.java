package controller;

import com.google.inject.Inject;
import model.BoardCell;
import views.BoardView;

public class StateController {

    BoardView boardView;

    public StateController(BoardView boardView) {
        this.boardView = boardView;
    }

    public void cellChangeOccured(BoardCell boardCell) {
//        TODO implement CellView rerendering knowing the boardCell
        System.out.println("Cell change occured");
    }
}
