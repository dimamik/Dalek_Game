package utils;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import model.Board;
import model.Vector2D;

public class GameUtil implements Runnable {
    private final PositionUtil positionUtil;
    private Board board;
    private volatile boolean userMoved;


    @Inject
    public GameUtil(Board board, PositionUtil positionUtil) {
        super();
        this.board = board;
        this.positionUtil = positionUtil;
        this.setUpGame();
    }

    private void setUpGame() {
//        Initial Game Set up
    }

    private void playRound() {
        Vector2D userMove = awaitGetUserMove();
//      TODO  Process user move

//      TODO Process Delek Moves

//      TODO Process Heaps


    }

    private boolean isGameOver() {
//        TODO Add Implementation
        return false;
    }

    public void runGame() {
//        Uses playRound and game logic from GameParameters to run the game
        while (!isGameOver()) {
            playRound();
        }

//        TODO Show to user who won and some stats

    }

    public void moveMade(ActionEvent actionEvent) {
        this.setUserMoved(true);
//        TODO Set userMoveVector to vector that you get
//        From events from user and somehow pass it to
//        the awaitGetUserMove

//        One possible solution is to use global variable
//        That can be concurrently set and read by this two methods

    }

    public Vector2D awaitGetUserMove() {
        while (!this.isUserMoved()) {
            Thread.onSpinWait();
        }
        this.setUserMoved(false);
//        TODO Add Logic to process user move
        return new Vector2D(1, 1);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isUserMoved() {
        return userMoved;
    }

    public void setUserMoved(boolean userMoved) {
        this.userMoved = userMoved;
    }

    @Override
    public void run() {
        runGame();
    }
}
