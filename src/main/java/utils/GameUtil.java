package utils;

import com.google.inject.Inject;
import model.Board;

public class GameUtil {
    private Board board;
    private PositionUtil positionUtil;

    @Inject
    public GameUtil(Board board) {
        this.board = board;
        this.positionUtil = new PositionUtil(board);
        this.setUpGame();
    }

    private void setUpGame() {
//        set up game

//        BoardObject boardObject = new BoardObject();
//        board.addBoardObject(boardObject, new Vector2D(0, 0));
//        board.removeBoardObject(boardObject, new Vector2D(0, 0));
    }

    private void playRound() {
    }

    public void runGame() {
//        uses playRound and game logic from GameParameters
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public PositionUtil getPositionUtil() {
        return positionUtil;
    }

    public void setPositionUtil(PositionUtil positionUtil) {
        this.positionUtil = positionUtil;
    }
}
