package controller;

import com.google.inject.Inject;
import com.google.inject.name.Named;
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
    private final int MAX_ROUNDS;
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
    @FXML
    public Button pauseGame;
    @FXML
    public Button backToMenu;
    private int roundNumber;

    //    FIXME NEEDS TO BE DIVIDED INTO SMALLER CONTROLLERS!
    @Inject
    public AppController(Board board, GameUtil gameUtil, BoardView boardView, @Named("roundsNumber") int roundsNumber) {
        this.board = board;
        this.gameUtil = gameUtil;
        this.boardView = boardView;
        this.MAX_ROUNDS = roundsNumber;
        this.gameStateController = new GameStateController(this);
    }

    @FXML
    private void initialize() {
        subscribeToCells();
        gameUtil.addListener(gameStateController);
        showMenu();
    }

    private void showMenu() {
        borderPane.setCenter(centerSide);
        this.borderPane.getRight().setVisible(false);
    }

    private void showGame() {
        borderPane.setCenter(boardView);
        movementButtons.setDisable(false);
        instructionsText.setText("Welcome to game!");
        pauseGame.setVisible(true);
        backToMenu.setVisible(true);
        resumeGameButton.setVisible(true);
        this.borderPane.getRight().setVisible(true);
    }

    public void startRandomGame() {
        gameUtil.resetGame();
        System.out.println(board);
        gameUtil.setUpRandomGame();
        backToMenu.setVisible(false);
        gameState = GameState.GAME_RUNNING;
    }

    public void startRoundGame() {
        if (roundNumber > MAX_ROUNDS) {
            endGame();
        }
        gameUtil.resetGame();
        backToMenu.setVisible(false);
        gameState = GameState.GAME_RUNNING;
        gameUtil.startNewDefinedRound(++roundNumber);
    }

    public void endGame() {
        setGameState(GameState.GAME_ENDED);
        movementButtons.setDisable(true);
        instructionsText.setText("Game over!");
        pauseGame.setVisible(false);
        backToMenu.setVisible(true);
        resumeGameButton.setVisible(false);
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

    public void onTeleportPress() {
        if (gameState == GameState.GAME_RUNNING) {
            gameUtil.handleTeleport();
        }
    }

    public void onTimeTravelPress() {
        if (gameState == GameState.GAME_RUNNING) {
            gameUtil.handleTimeTravel();
        }
    }

    @Override
    public void onEvent(BoardCell boardCell) {
        BoardCellView boardCellView = boardView.getBoardCellView(boardCell.getPosition().x(), boardCell.getPosition().y());
        if (boardCell.getTopBoardObject().isPresent()) {
            BoardObject boardObject = boardCell.getTopBoardObject().get();
            boardCellView.drawBoardObjectView(new BoardObjectView(boardObject));
        } else {
            boardCellView.clearBoardObjectView();
        }
    }

    public void handleResumeGame() {
        showGame();
    }

    public void handlePlayRoundGame() {
        roundNumber = 0;
        showGame();
        startRoundGame();
    }

    public void handlePlayRandomGame() {
        showGame();
        startRandomGame();
    }

    public void handlePauseGame() {
        resumeGameButton.setVisible(true);
        showMenu();
    }

    public void handleGoBackToMenu() {
        showMenu();
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
