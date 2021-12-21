package utils;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import javafx.event.ActionEvent;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import model.board_object_instances.Dalek;
import model.board_object_instances.Doctor;

import java.util.concurrent.ThreadLocalRandom;

public class GameUtil implements Runnable {
    private final PositionUtil positionUtil;
    private Board board;
    private volatile boolean userMoved;
    private int daleksNo;
    private BoardObject doctor;


    @Inject
    public GameUtil(Board board, PositionUtil positionUtil, @Named("daleksNo") int daleksNo) {
        super();
        this.board = board;
        this.positionUtil = positionUtil;
        this.daleksNo = daleksNo;
    }

    public void setUpGame() {
        this.doctor = new Doctor();
        positionUtil.getBoard().addBoardObject(doctor, new Vector2D(10, 10));

        placeDaleks(daleksNo);

//        board.addBoardObject(new Dalek(), new Vector2D(0,0));
//        board.addBoardObject(new Dalek(), new Vector2D(19,19));
//        positionUtil.changePosition(board.getBoardCell(new Vector2D(1,1)), new Vector2D(1,1));
//        positionUtil.changePosition(positionUtil.getBoard().getBoardCell(new Vector2D(11, 10)), new Vector2D(1,1));
    }

    private void placeDaleks(int numberOfDaleks) {
        for (int i = 0; i < numberOfDaleks; i++) {
            boolean isOccupied = false;

            Vector2D spawnPlace = null;

            while (!isOccupied) {
                int newX = ThreadLocalRandom.current().nextInt(0, this.board.getCols());
                int newY = ThreadLocalRandom.current().nextInt(0, this.board.getRows());

                spawnPlace = new Vector2D(newX, newY);

                isOccupied = this.board.getBoardCell(spawnPlace).isEmpty();
            }

            this.board.addBoardObject(new Dalek(), spawnPlace);
        }
    }

    private void playRound() {
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

    public void handleMove(ActionEvent actionEvent) {
        Vector2D doctorPosition = positionUtil.getBoardObjectPosition(doctor);
        Vector2D direction = positionUtil.getDirection(actionEvent);
        BoardCell sourceCell = board.getBoardCell(doctorPosition);

        positionUtil.move(sourceCell, direction);

        if (positionUtil.isGameEnded()) {
            System.out.println("GAME ENDED!");
        }

        positionUtil.moveAllDaleks(doctorPosition.add(direction));

//        TODO - sprawdzenie czy gra sie skonczyla
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
