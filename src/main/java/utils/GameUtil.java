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
import model.board_object_instances.TimeTravel;

import java.util.*;

@Slf4j
public class GameUtil extends EventEmitter<GameState> implements EventListener<GameState> {
    private final PositionUtil positionUtil;
    private final Board board;
    private final GameStateHistoryUtil gameStateHistoryUtil;
    private final MapStartStateUtil mapStartStateUtil;
    private final List<BoardCell> occupiedCells;
    //    TODO Change this to injected  from game module values
    private final double TELEPORT_PROBABILITY = 0.6;
    private final double TIME_TRAVEL_PROBABILITY = 0.6;
    public int teleportsNumber = 0;
    public int timeTravelNumber = 0;
    int numberOfDaleks;
    private BoardCell doctorCell;

    @Inject
    public GameUtil(Board board, PositionUtil positionUtil, MapStartStateUtil mapStartStateUtil, @Named("daleksNo") int daleksNo) {
        super();
        this.board = board;
        this.numberOfDaleks = daleksNo;
        this.positionUtil = positionUtil;
        this.occupiedCells = new ArrayList<>();
        this.gameStateHistoryUtil = new GameStateHistoryUtil();
        this.mapStartStateUtil = mapStartStateUtil;
        init();
    }

    private void init() {
        this.positionUtil.addListener(this);

    }

    public void handleTeleport(Button teleport) {
        if (teleportsNumber > 0) {
            teleportsNumber--;
//            TODO Extract duplicate code
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
        teleport.setText("TELEPORT: " + teleportsNumber);
    }

    public void handleTimeTravel(Button timeTravel) {
        if (timeTravelNumber > 0) {
            Optional<HashMap<Vector2D, LinkedList<BoardObject>>> lastDay = gameStateHistoryUtil.popLastDay();
            if (lastDay.isPresent()) {
                timeTravelNumber--;
                board.clearBoard();
                occupiedCells.clear();
                for (Vector2D cellPosition : lastDay.get().keySet()) {
                    BoardCell cell = board.getBoardCell(cellPosition);
                    LinkedList<BoardObject> objectsToAdd = lastDay.get().get(cellPosition);
                    for (BoardObject object : objectsToAdd) {
                        if (object.getType() == ObjectType.DOCTOR) {
                            doctorCell = cell;
                        }
                        cell.addBoardObject(object);

                    }
                    occupiedCells.add(cell);
                }
            }
        }
        timeTravel.setText("TIME TRAVEL: " + timeTravelNumber);
    }

    public void setUpRandomGame() {
        resetGame();
        BoardObject doctor = new Doctor();
        Vector2D doctorInitPosition = new Vector2D(board.getCols() / 2, board.getRows() / 2);
        board.addBoardObject(doctor, doctorInitPosition);
        doctorCell = board.getBoardCell(doctorInitPosition);
        this.mapStartStateUtil.placeRandomly(occupiedCells, numberOfDaleks);
    }

    public void resetGame() {
        gameStateHistoryUtil.reset();
        occupiedCells.clear();
//        teleportsNumber = 0;
//        timeTravelNumber = 0;
        board.clearBoard();
    }

    public void startNewDefinedRound(int roundNumber) {
        resetGame();
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

        spawnTeleport();
        spawnTimeTravel();

    }

    private void spawnTeleport() {
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

    private void spawnTimeTravel() {
        if (Math.random() < TIME_TRAVEL_PROBABILITY) {
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
            board.addBoardObject(new TimeTravel(), freeCells.get(randomIndex).getPosition());
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

    public boolean didDoctorWin() {
        if (!doctorExists(doctorCell)) {
            log.info("YOU LOOSE!");
            return false;
        } else if (!atLeastOneDalekExists(occupiedCells)) {
            log.info("YOU WIN!");
            return true;
        }
        throw new IllegalStateException("Game is not ended");
    }


    private void gameEnded() {
        log.info("GAME ENDED!");
        if (didDoctorWin()) {
            emit(GameState.NEXT_ROUND);
        } else {
            emit(GameState.GAME_ENDED);
        }
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void onEvent(GameState e) {
        if (e == GameState.TELEPORT_GAINED) {
//          FIXME This can be done in better way!
            teleportsNumber++;
            emit(GameState.TELEPORT_GAINED);

        } else if (e == GameState.TIME_TRAVEL_GAINED) {
            timeTravelNumber++;
            emit(GameState.TIME_TRAVEL_GAINED);
        }

    }
}

