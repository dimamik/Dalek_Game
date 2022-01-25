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
    public final int maxRounds;
    public final GameUtil gameUtil;
    public final BoardView boardView;
    private final Board board;
    private final GameStateController gameStateController;
    public GameState gameState = GameState.GAME_PAUSED;
    public int roundNumber = 0;
    public boolean campaignMode;

    @FXML
    public VBox centerSide;
    @FXML
    public Button resumeGameButton;
    @FXML
    public VBox rightSide;
    @FXML
    public VBox movementButtons;
    @FXML
    public BorderPane borderPane;
    @FXML
    public Label infoLabel;
    @FXML
    public Button teleport;
    @FXML
    public Button timeTravel;
    @FXML
    public Button pauseGame;
    @FXML
    public Button backToMenu;

    @Inject
    public AppController(Board board, GameUtil gameUtil, BoardView boardView, @Named("roundsNumber") int roundsNumber) {
        this.board = board;
        this.gameUtil = gameUtil;
        this.boardView = boardView;
        this.maxRounds = roundsNumber;
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
        infoLabel.setText("Have Fun!");
        pauseGame.setVisible(true);
        backToMenu.setVisible(true);
        resumeGameButton.setVisible(true);
        this.borderPane.getRight().setVisible(true);
    }

    private void controlTeleport() {
        teleport.setDisable(gameUtil.getTeleportsNumber() <= 0);
    }

    private void controlTimeTravel() {
        timeTravel.setDisable(gameUtil.getTimeTravelNumber() <= 0);

    }

    public void startQuickGame() {
        gameUtil.resetGame();
        gameUtil.setUpRandomGame();
        campaignMode = false;
        backToMenu.setVisible(false);
        teleport.setText("TELEPORT: " + gameUtil.getTeleportsNumber());
        timeTravel.setText("TIME TRAVEL: " + gameUtil.getTimeTravelNumber());
        controlTeleport();
        controlTimeTravel();
        setGameState(GameState.PLAYING_RANDOM);
    }

    public void startCampaignGame() {
        roundNumber = roundNumber + 1;
        if (roundNumber > maxRounds) {
            endGame();
            roundNumber = 0;
        }
        gameUtil.resetGame();
        backToMenu.setVisible(false);
        campaignMode = true;
        teleport.setText("TELEPORT: " + gameUtil.getTeleportsNumber());
        timeTravel.setText("TIME TRAVEL: " + gameUtil.getTimeTravelNumber());
        controlTeleport();
        controlTimeTravel();
        setGameState(GameState.PLAYING_ROUND);
        gameUtil.startNewDefinedRound(roundNumber);
        infoLabel.setText("ROUND " + roundNumber);
    }

    public void endGame() {
        setGameState(GameState.GAME_ENDED);
        movementButtons.setDisable(true);
        teleport.setDisable(true);
        timeTravel.setDisable(true);
        infoLabel.setText("Game over!");
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

    private boolean isGameActive() {
        return gameState == GameState.PLAYING_RANDOM || gameState == GameState.PLAYING_ROUND;
    }

    public void onDirectionPress(ActionEvent actionEvent) {
        if (isGameActive()) {
            String eventTarget = actionEvent.getTarget().toString();
            String directionString = eventTarget.substring(eventTarget.indexOf("'") + 1, eventTarget.lastIndexOf("'"));

            this.gameUtil.handleMove(directionString);
        }
    }

    public void onTeleportPress() {
        if (isGameActive()) {
            gameUtil.handleTeleport(teleport);
        }
    }

    public void onTimeTravelPress() {
        if (isGameActive()) {
            gameUtil.handleTimeTravel(timeTravel);
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
        startCampaignGame();
    }

    public void handlePlayRandomGame() {
        showGame();
        startQuickGame();
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

    public void updateTeleportButton() {
        teleport.setText("TELEPORT: " + gameUtil.getTeleportsNumber());
    }

    public void updateTimeTravelButton() {
        timeTravel.setText("TIME TRAVEL: " + gameUtil.getTimeTravelNumber());
    }
}
