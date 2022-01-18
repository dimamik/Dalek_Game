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
    private final double TELEPORT_PROBABILITY;
    private final double TIME_TRAVEL_PROBABILITY;
    public int teleportsNumber = 0;
    public int timeTravelNumber = 0;
    int numberOfDaleks;
    private BoardCell doctorCell;

    @Inject
    public GameUtil(Board board, PositionUtil positionUtil, MapStartStateUtil mapStartStateUtil,
                    @Named("daleksNo") int daleksNo,
                    @Named("TELEPORT_PROBABILITY") double TELEPORT_PROBABILITY,
                    @Named("TIME_TRAVEL_PROBABILITY") double TIME_TRAVEL_PROBABILITY) {
        super();
        this.board = board;
        this.numberOfDaleks = daleksNo;
        this.positionUtil = positionUtil;
        this.occupiedCells = new ArrayList<>();
        this.gameStateHistoryUtil = new GameStateHistoryUtil();
        this.mapStartStateUtil = mapStartStateUtil;
        this.TELEPORT_PROBABILITY = TELEPORT_PROBABILITY;
        this.TIME_TRAVEL_PROBABILITY = TIME_TRAVEL_PROBABILITY;
        init();
    }

    private void init() {
        this.positionUtil.addListener(this);

    }

    public Optional<BoardCell> findRandomEmptyCell() {
        List<BoardCell> freeCells = new ArrayList<>();
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                BoardCell cell = board.getBoardCell(new Vector2D(i, j));
                if (!occupiedCells.contains(cell)) {
                    freeCells.add(cell);
                }
            }
        }
        if (freeCells.size() == 0) {
            return Optional.empty();
        }

        int emptyIndex = (int) (Math.random() * freeCells.size());
        return Optional.of(freeCells.get(emptyIndex));
    }

    public void handleTeleport(Button teleport) {
        if (teleportsNumber > 0) {
            teleportsNumber--;

            Optional<BoardCell> boardCell = findRandomEmptyCell();
            boardCell.ifPresent(cell -> {
                if (cell.isEmpty()) {
                    BoardObject doctorObject = doctorCell.getBoardObjects().get(0);
                    doctorCell.removeBoardObject(doctorCell.getBoardObjects().get(0));
                    boardCell.get().addBoardObject(doctorObject);
                    occupiedCells.add(boardCell.get());
                    occupiedCells.remove(doctorCell);
                    doctorCell = boardCell.get();
                }
            });
        }
        if (teleportsNumber == 0) {
            teleport.setDisable(true);
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
        if (timeTravelNumber == 0) {
            timeTravel.setDisable(true);
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
        teleportsNumber = 0;
        timeTravelNumber = 0;
        gameStateHistoryUtil.reset();
        occupiedCells.clear();
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
            Optional<BoardCell> boardCell = findRandomEmptyCell();
            boardCell.ifPresent(cell -> {
                if (cell.isEmpty()) {
                    board.addBoardObject(new Teleport(), boardCell.get().getPosition());
                    occupiedCells.add(boardCell.get());
                }
            });
        }
    }

    private void spawnTimeTravel() {
        if (Math.random() < TIME_TRAVEL_PROBABILITY) {
            Optional<BoardCell> boardCell = findRandomEmptyCell();
            boardCell.ifPresent(cell -> {
                if (cell.isEmpty()) {
                    board.addBoardObject(new TimeTravel(), boardCell.get().getPosition());
                    occupiedCells.add(boardCell.get());
                }
            });
        }
    }

    public boolean noDaleksOnBoard(List<BoardCell> occupiedCells) {
        for (BoardCell cell : occupiedCells) {
            if (!cell.isEmpty() && cell.getBoardObjects().get(0).getType() == ObjectType.DALEK) {
                return false;
            }
        }
        return true;
    }

    public boolean noDoctorOnBoard(BoardCell doctorCell) {
        if (doctorCell.getTopBoardObject().isPresent()) {
            for (BoardObject boardObject : doctorCell.getBoardObjects()) {
                if (boardObject.getType() == ObjectType.DOCTOR) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGameEnded() {
        if (noDoctorOnBoard(doctorCell)) {
            return true;
        } else return noDaleksOnBoard(occupiedCells);
    }

    public boolean didDoctorWin() {
        if (noDoctorOnBoard(doctorCell)) {
            return false;
        } else if (noDaleksOnBoard(occupiedCells)) {
            return true;
        }
        throw new IllegalStateException("Game is not ended!");
    }


    private void gameEnded() {
        if (didDoctorWin()) {
            emit(GameState.DOCTOR_WON);
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
            teleportsNumber++;
            emit(GameState.TELEPORT_GAINED);

        } else if (e == GameState.TIME_TRAVEL_GAINED) {
            timeTravelNumber++;
            emit(GameState.TIME_TRAVEL_GAINED);
        }
    }
}

