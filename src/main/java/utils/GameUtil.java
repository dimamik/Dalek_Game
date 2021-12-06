package utils;

import com.google.inject.Inject;
import javafx.scene.paint.Color;
import model.Board;
import model.Vector2D;
import model.board_object_instances.Cat;

public class GameUtil {
    private Board board;
    private PositionUtil positionUtil;

    @Inject
    public GameUtil(Board board, PositionUtil positionUtil) {
        this.board = board;
        this.positionUtil = positionUtil;
        this.setUpGame();
    }

    private void setUpGame() {
//        Initial Game Set up
    }

    private void playRound(int roundNumber) {
//        TODO This should be in a separate thread
        if (roundNumber % 2 == 0) {
            board.addBoardObject(new Cat(Color.BLACK), new Vector2D(0, roundNumber));
        } else {
            board.addBoardObject(new Cat(Color.WHITE), new Vector2D(0, roundNumber));
        }

        if (roundNumber >= 1) {
//        Make an upper move
            positionUtil.changePosition(board.getBoardCell(new Vector2D(0, roundNumber)), new Vector2D(roundNumber, 0));
        }
    }

    public void runGame() {
//        Uses playRound and game logic from GameParameters to run the game
        for (int i = 0; i < 10; i++) {
            playRound(i);
        }

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
