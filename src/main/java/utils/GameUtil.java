package utils;

import com.google.inject.Inject;
import model.Board;

public class GameUtil {
    private Board board;
    private PositionUtil positionUtil;

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


    @Inject
    public GameUtil(Board board) {
        this.board = board;
        this.positionUtil = new PositionUtil(board);
        this.setUpGame();
    }

    private void setUpGame() {

    }

    private void playRound() {

    }

    public void runGame() {
//        uses playRound and game logic
    }

}
