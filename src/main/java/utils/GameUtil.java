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
import model.board_object_instances.Doctor;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GameUtil extends EventEmitter<GameState> implements EventListener<GameState> {
    private final PositionUtil positionUtil;
    private final Board board;
    private final GameStateHistoryUtil gameStateHistoryUtil;
    private final MapStartStateUtil mapStartStateUtil;
    private int TELEPORTS_NUMBER = 0;

    private int TIME_TRAVEL_NUMBER = 0;
    BoardCell doctorCell;

    List<BoardCell> occupiedCells = new ArrayList<>();

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
        this.positionUtil = positionUtil;
        this.gameStateHistoryUtil = new GameStateHistoryUtil();
//        FIXME This needs to be done in a better way
        this.mapStartStateUtil = new MapStartStateUtil(board, new MapStartParams(daleksNo));
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

//        placeDaleks(daleksNo);
        this.mapStartStateUtil.placeDaleks(occupiedCells);

    }


//    TODO Remove unused
//    private void placeDaleks(int numberOfDaleks) {
//
//        List<Vector2D> availableSpots = new ArrayList<>();
//        for (int i = 0; i < board.getRows(); i++)
//            for (int j = 0; j < board.getRows(); j++)
//                if (board.getBoardCell(new Vector2D(i, j)).isEmpty())
//                    availableSpots.add(new Vector2D(i, j));
//
//        for (int i = 0; i < numberOfDaleks; i++) {
//            int randomIndex = ThreadLocalRandom.current().nextInt(0, availableSpots.size());
//            Vector2D spawnPlace = availableSpots.get(randomIndex);
//            this.board.addBoardObject(new Dalek(), spawnPlace);
//            occupiedCells.add(board.getBoardCell(spawnPlace));
//            availableSpots.remove(randomIndex);
//        }
//    }

    public void handleMove(String directionString) {
//        TODO Add Previous State to StateTable
//        FIXME This can be a commander pattern
        gameStateHistoryUtil.recordDay(board);


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

    public Board getBoard() {
        return board;
    }

    @Override
    public void onEvent(GameState e) {
        if (e == GameState.TELEPORT_GAINED) {
//            FIXME This can be setters
            TELEPORTS_NUMBER++;
        } else if (e == GameState.TIME_TRAVEL_GAINED) {
            TIME_TRAVEL_NUMBER++;
        } else {
            log.error("GameUtil: onEvent: " + e);
        }
    }
}

