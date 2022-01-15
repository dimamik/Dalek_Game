package utils;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import enums.GameState;
import enums.ObjectType;
import interfaces.EventEmitter;
import interfaces.EventListener;
import lombok.extern.slf4j.Slf4j;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import model.board_object_instances.Doctor;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GameUtil extends EventEmitter<GameState> implements EventListener<GameState> {
    private final PositionUtil positionUtil;
    private final Board board;
    private final GameStateHistoryUtil gameStateHistoryUtil;
    private final MapStartStateUtil mapStartStateUtil;
    private final List<BoardCell> occupiedCells;
    int numberOfDaleks;
    private int TELEPORTS_NUMBER = 0;
    private int TIME_TRAVEL_NUMBER = 0;
    private BoardCell doctorCell;

    @Inject
    public GameUtil(Board board, PositionUtil positionUtil, @Named("daleksNo") int daleksNo) {
//        FIXME This needs to work the following way:
//        First of all we initialize the game with predefined map/parameters, validated before
//        This game is like a server, and data is coming from client, so input validation is needed
//        Then game is played like before, and app is controlling the game global state (game ended, game started, etc)
//        Also App is responsible for game menu and handling user interaction
//        Later App needs to be split into multiple modules, so that it can be easily tracked and tested

        super();
        this.board = board;
        this.numberOfDaleks = daleksNo;
        this.positionUtil = positionUtil;
        this.occupiedCells = new ArrayList<>();
        this.gameStateHistoryUtil = new GameStateHistoryUtil();
//        FIXME This needs to be done in a better way
        this.mapStartStateUtil = new MapStartStateUtil(board);
    }

    public void handleTeleport() {
        if (TELEPORTS_NUMBER > 0) {
            TELEPORTS_NUMBER--;
//            Find a random cell that is not occupied
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
        }
    }

    public void handleTimeTravel() {
        if (TIME_TRAVEL_NUMBER > 0) {
            TIME_TRAVEL_NUMBER--;
//            TODO Handle
        }
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
        TELEPORTS_NUMBER = 0;
        TIME_TRAVEL_NUMBER = 0;
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
    }

    public boolean atLeastOneDalekExists(List<BoardCell> occupiedCells) {
        for (BoardCell cell : occupiedCells) {
            if (cell.getBoardObjects().get(0).getType() == ObjectType.DALEK) {
                return true;
            }
        }
        return false;
    }

    public boolean doctorExists(BoardCell doctorCell) {
        if (doctorCell.getTopBoardObject().isPresent()) {
            return doctorCell.getBoardObjects().get(0).getType() == ObjectType.DOCTOR;
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
//        resetGame();
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
            TELEPORTS_NUMBER++;
        } else if (e == GameState.TIME_TRAVEL_GAINED) {
            TIME_TRAVEL_NUMBER++;
        } else {
            log.error("GameUtil: onEvent: " + e);
        }
    }
}

