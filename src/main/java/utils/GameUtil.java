package utils;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import enums.GameState;
import interfaces.EventEmitter;
import interfaces.EventListener;
import lombok.extern.slf4j.Slf4j;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import model.board_object_instances.Dalek;
import model.board_object_instances.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class GameUtil extends EventEmitter<GameState> implements EventListener<GameState> {
    private final PositionUtil positionUtil;
    private final Board board;
    private final int daleksNo;

    private int TELEPORTS_NUMBER = 0;

    private int TIME_TRAVEL_NUMBER = 0;
    BoardCell doctorCell;

    List<BoardCell> occupiedCells = new ArrayList<>();

    @Inject
    public GameUtil(Board board, PositionUtil positionUtil, @Named("daleksNo") int daleksNo) {
        super();
        this.board = board;
        this.positionUtil = positionUtil;
        this.daleksNo = daleksNo;
    }

    public void handleTeleport() {
        if (TELEPORTS_NUMBER > 0) {
            TELEPORTS_NUMBER--;
//            TODO Handle
        }
    }

    public void handleTimeTravel() {
        if (TIME_TRAVEL_NUMBER > 0) {
            TIME_TRAVEL_NUMBER--;
//            TODO Handle
        }
    }

    public void setUpGame() {
        BoardObject doctor = new Doctor();
        Vector2D doctorInitPosition = new Vector2D(board.getCols() / 2, board.getRows() / 2);
        positionUtil.getBoard().addBoardObject(doctor, doctorInitPosition);
        doctorCell = getBoard().getBoardCell(doctorInitPosition);

        placeDaleks(daleksNo);
    }

    private void placeDaleks(int numberOfDaleks) {

        List<Vector2D> availableSpots = new ArrayList<>();
        for (int i = 0; i < board.getRows(); i++)
            for (int j = 0; j < board.getRows(); j++)
                if (board.getBoardCell(new Vector2D(i, j)).isEmpty())
                    availableSpots.add(new Vector2D(i, j));

        for (int i = 0; i < numberOfDaleks; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, availableSpots.size());
            Vector2D spawnPlace = availableSpots.get(randomIndex);
            this.board.addBoardObject(new Dalek(), spawnPlace);
            occupiedCells.add(board.getBoardCell(spawnPlace));
            availableSpots.remove(randomIndex);
        }
    }

    public void handleMove(String directionString) {
        Vector2D direction = positionUtil.getDirection(directionString);

        doctorCell = positionUtil.move(doctorCell, direction);

        Vector2D doctorPositionAfterMove = doctorCell.getPosition();

        if (positionUtil.isGameEnded(occupiedCells, doctorCell)) {
            gameEnded();
            return;
        }

        positionUtil.moveAllDaleks(doctorPositionAfterMove, occupiedCells);
        if (positionUtil.isGameEnded(occupiedCells, doctorCell)) {
            gameEnded();
        }
    }


    private void gameEnded() {
        log.info("GAME ENDED!");
        emit(GameState.GAME_ENDED);
    }

    public int getTELEPORTS_NUMBER() {
        return TELEPORTS_NUMBER;
    }

    public void setTELEPORTS_NUMBER(int TELEPORTS_NUMBER) {
        this.TELEPORTS_NUMBER = TELEPORTS_NUMBER;
    }

    public int getTIME_TRAVEL_NUMBER() {
        return TIME_TRAVEL_NUMBER;
    }

    public void setTIME_TRAVEL_NUMBER(int TIME_TRAVEL_NUMBER) {
        this.TIME_TRAVEL_NUMBER = TIME_TRAVEL_NUMBER;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void onEvent(GameState e) {
        if (e == GameState.TELEPORT_GAINED) {
            TELEPORTS_NUMBER++;
        }
        else if (e == GameState.TIME_TRAVEL_GAINED) {
            TIME_TRAVEL_NUMBER++;
        }
        else{
            log.error("GameUtil: onEvent: " + e);
        }
    }
}

