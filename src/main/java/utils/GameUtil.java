package utils;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import enums.GameState;
import enums.ObjectType;
import interfaces.EventEmitter;
import interfaces.EventListener;
import javafx.scene.control.Button;
import lombok.extern.slf4j.Slf4j;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import model.board_object_instances.Doctor;
import model.board_object_instances.Teleport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class GameUtil extends EventEmitter<GameState> implements EventListener<GameState> {
    private final PositionUtil positionUtil;
    private final Board board;
    private final GameStateHistoryUtil gameStateHistoryUtil;
    private final MapStartStateUtil mapStartStateUtil;
    private final List<BoardCell> occupiedCells;
    private final double TELEPORT_PROBABILITY = 0.6;
    public int teleportsNumber = 10;
    public int timeTravelNumber = 10;
    int numberOfDaleks;
    private BoardCell doctorCell;

    @Inject
    public GameUtil(Board board, PositionUtil positionUtil, @Named("daleksNo") int daleksNo) {
        super();
        this.board = board;
        this.numberOfDaleks = daleksNo;
        this.positionUtil = positionUtil;
        this.occupiedCells = new ArrayList<>();
        this.gameStateHistoryUtil = new GameStateHistoryUtil();
//        FIXME This needs to be done in a better way
        this.mapStartStateUtil = new MapStartStateUtil(board);
    }

    public void handleTeleport(Button teleport) {
        if (teleportsNumber > 0) {
            teleportsNumber--;
            List<BoardCell> freeCells = new ArrayList<>();
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getCols(); j++) {
                    BoardCell cell = board.getBoardCell(new Vector2D(i, j));
                    if (!occupiedCells.contains(cell)) {
                        freeCells.add(cell);
                    }
                }
            }
            int randomIndex = (int) (Math.random() * freeCells.size());
            BoardObject doctorObject = doctorCell.getBoardObjects().get(0);
            doctorCell.removeBoardObject(doctorCell.getBoardObjects().get(0));
            freeCells.get(randomIndex).addBoardObject(doctorObject);
            occupiedCells.add(freeCells.get(randomIndex));
            occupiedCells.remove(doctorCell);
            doctorCell = freeCells.get(randomIndex);
        }
        teleport.setText("TELEPORTS: " + teleportsNumber);
    }

    public void handleTimeTravel(Button timeTravel) {
        if (timeTravelNumber > 0) {
            timeTravelNumber--;
            board.clearBoard();
            occupiedCells.clear();
            HashMap<Vector2D, LinkedList<BoardObject>> lastDay = gameStateHistoryUtil.popLastDay();
            for (Vector2D cellPosition : lastDay.keySet()) {
                BoardCell cell = board.getBoardCell(cellPosition);
                LinkedList<BoardObject> objectsToAdd = lastDay.get(cellPosition);
                for (BoardObject object : objectsToAdd) {
                    if (object.getType() == ObjectType.DOCTOR) {
                        doctorCell = cell;
                    }
                    cell.addBoardObject(object);

                }
                occupiedCells.add(cell);
            }

        }
        timeTravel.setText("TIME TRAVEL: " + timeTravelNumber);
    }

    public void setUpRandomGame() {
        BoardObject doctor = new Doctor();
        Vector2D doctorInitPosition = new Vector2D(board.getCols() / 2, board.getRows() / 2);
        board.addBoardObject(doctor, doctorInitPosition);
        doctorCell = board.getBoardCell(doctorInitPosition);
        this.mapStartStateUtil.placeRandomly(occupiedCells, numberOfDaleks);
    }

    public void resetGame() {
        gameStateHistoryUtil.reset();
        occupiedCells.clear();
//        TELEPORTS_NUMBER = 0;
//        TIME_TRAVEL_NUMBER = 0;
        board.clearBoard();
    }

    public void startNewDefinedRound(int roundNumber) {
        BoardObject doctor = new Doctor();
        Vector2D doctorInitPosition = new Vector2D(board.getCols() / 2, board.getRows() / 2);
        board.addBoardObject(doctor, doctorInitPosition);
        doctorCell = board.getBoardCell(doctorInitPosition);
        this.mapStartStateUtil.placeFromDatabase(occupiedCells, roundNumber);
    }

    public void handleMove(String directionString) {
//        TODO Add Previous State to StateTable
//        FIXME This can be a commander pattern
        gameStateHistoryUtil.recordDay(board);

        Vector2D direction = positionUtil.getDirection(directionString);

        doctorCell = positionUtil.move(doctorCell, direction);

        Vector2D doctorPositionAfterMove = doctorCell.getPosition();

        if (isGameEnded()) {
            gameEnded();
            return;
        }

        positionUtil.moveAllDaleks(doctorPositionAfterMove, occupiedCells);

        if (isGameEnded()) {
            gameEnded();
        }

//        Spawn a teleport if randomly needed
        if (Math.random() < TELEPORT_PROBABILITY) {
//          Find a free cell
//          TODO THIS IS DUPLCIATE CODE, NEEDS TO BE FIXED
            List<BoardCell> freeCells = new ArrayList<>();
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getCols(); j++) {
                    BoardCell cell = board.getBoardCell(new Vector2D(i, j));
                    if (!occupiedCells.contains(cell)) {
                        freeCells.add(cell);

                    }
                }
            }
            int randomIndex = (int) (Math.random() * freeCells.size());
            board.addBoardObject(new Teleport(), freeCells.get(randomIndex).getPosition());
            occupiedCells.add(freeCells.get(randomIndex));
        }

    }

    public boolean atLeastOneDalekExists(List<BoardCell> occupiedCells) {
        for (BoardCell cell : occupiedCells) {
            if (!cell.isEmpty() && cell.getBoardObjects().get(0).getType() == ObjectType.DALEK) {
                return true;
            }
        }
        return false;
    }

    public boolean doctorExists(BoardCell doctorCell) {
        if (doctorCell.getTopBoardObject().isPresent()) {
            for (BoardObject boardObject : doctorCell.getBoardObjects()) {
                if (boardObject.getType() == ObjectType.DOCTOR) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isGameEnded() {
        if (!doctorExists(doctorCell)) {
            log.info("YOU LOOSE!");
            return true;
        } else if (!atLeastOneDalekExists(occupiedCells)) {
            log.info("YOU WIN!");
            return true;
        }
        return false;
    }


    private void gameEnded() {
        log.info("GAME ENDED!");
        emit(GameState.GAME_ENDED);
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void onEvent(GameState e) {
        if (e == GameState.TELEPORT_GAINED) {
//          FIXME This can be setters
            teleportsNumber++;
            emit(GameState.TELEPORT_GAINED);

        } else if (e == GameState.TIME_TRAVEL_GAINED) {
            timeTravelNumber++;
            emit(GameState.TIME_TRAVEL_GAINED);
        }
        log.error("GameUtil: onEvent: " + e);

    }
}

