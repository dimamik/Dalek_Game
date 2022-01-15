package controller;

import com.google.inject.Inject;
import enums.GameState;
import interfaces.EventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import utils.GameUtil;
import views.BoardCellView;
import views.BoardObjectView;
import views.BoardView;

/**
 * Is responsible for Controlling Board with its objects and movement keys
 * Later we will need to split this up into board controller and menu controller
 */
public class AppController implements EventListener<BoardCell> {
    private final Board board;
    private final GameStateController gameStateController;
    @FXML
    public VBox centerSide;
    @FXML
    public Button resumeGameButton;
    public GameUtil gameUtil;
    public GameState gameState = GameState.GAME_PAUSED;
    @FXML
    public VBox rightSide;
    @FXML
    public VBox movementButtons;
    @FXML
    public BorderPane borderPane;
    public BoardView boardView;
    @FXML
    public Label instructionsText;
    @FXML
    public Button Teleport;
    @FXML
    public Button TimeTravel;
    private int roundNumber = 0;

    //    FIXME NEEDS TO BE DIVIDED INTO SMALLER CONTROLLERS!
    @Inject
    public AppController(Board board, GameUtil gameUtil, BoardView boardView) {
        this.board = board;
        this.gameUtil = gameUtil;
        this.boardView = boardView;
//      FIXME This part is ugly, we need to inject GameStateController somehow, but it generates recursion
        this.gameStateController = new GameStateController(this);
    }

    @FXML
    private void initialize() {
        subscribeToCells();
        gameUtil.addListener(gameStateController);
//        initViews();
        showMenu();
    }

    private void showGame() {
        borderPane.setCenter(boardView);
        this.borderPane.getRight().setVisible(true);
    }

    private void showMenu() {
        borderPane.setCenter(centerSide);
        this.borderPane.getRight().setVisible(false);
    }

    public void startGame() {
        gameUtil.setUpGame();
        gameState = GameState.GAME_RUNNING;
    }

    public void startNextRound() {
//      TODO Check if there are more rounds to play
        gameUtil.restartGame();
        gameUtil.startNewDefinedRound(++roundNumber);
    }

    private void initViews() {
        borderPane.centerProperty().setValue(boardView);
//        borderPane.setCenter(boardView);
    }

    private void subscribeToCells() {
        for (int i = 0; i < board.getCols(); i++) {
            for (int j = 0; j < board.getRows(); j++) {
                board.getBoardCell(new Vector2D(i, j)).addListener(this);
            }
        }
    }

    public void onDirectionPress(ActionEvent actionEvent) {
        if (gameState == GameState.GAME_RUNNING) {
            String eventTarget = actionEvent.getTarget().toString();
            String directionString = eventTarget.substring(eventTarget.indexOf("'") + 1, eventTarget.lastIndexOf("'"));

            this.gameUtil.handleMove(directionString);
        }
    }

    public void onTeleportPress(ActionEvent actionEvent) {
        if (gameState == GameState.GAME_RUNNING) {
            gameUtil.handleTeleport();
        }
    }

    public void onTimeTravelPress(ActionEvent actionEvent) {
        if (gameState == GameState.GAME_RUNNING) {
            gameUtil.handleTimeTravel();
        }
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void onEvent(BoardCell boardCell) {
        BoardCellView boardCellView = boardView.getBoardCellView(boardCell.getPosition().x(), boardCell.getPosition().y());
        if (boardCell.getTopBoardObject().isPresent()) {
            BoardObject boardObject = boardCell.getTopBoardObject().get();
            //        TODO there BoardObjectView needs to be injected
            boardCellView.drawBoardObjectView(new BoardObjectView(boardObject));
        } else {
            boardCellView.clearBoardObjectView();
        }
    }


    public void handleResumeGame(ActionEvent actionEvent) {
        showGame();
    }

    public void handlePLayRoundGame(ActionEvent actionEvent) {
        showGame();
        startNextRound();
    }

    public void handlePlayRandomGame(ActionEvent actionEvent) {
        showGame();
        startGame();
    }

    public void handlePauseGame(ActionEvent actionEvent) {
        resumeGameButton.setVisible(true);
        showMenu();
    }
}
