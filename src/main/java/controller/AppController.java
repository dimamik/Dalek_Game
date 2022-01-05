package controller;

import com.google.inject.Inject;
import enums.GameState;
import interfaces.EventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public GameUtil gameUtil;
    public GameState gameState = GameState.GAME_RUNNING;
    @FXML
    public VBox rightSide;
    @FXML
    public VBox movementButtons;
    @FXML
    public BorderPane borderPane;
    public BoardView boardView;
    @FXML
    public Label instructionsText;

    @Inject
    public AppController(Board board, GameUtil gameUtil, BoardView boardView) {
        this.board = board;
        this.gameUtil = gameUtil;
        this.boardView = boardView;
        this.gameStateController = new GameStateController(this);

    }

    @FXML
    private void initialize() {
        subscribeToCells();
        gameUtil.addListener(gameStateController);
        initViews();
        gameUtil.setUpGame();
    }

    private void initViews() {
        borderPane.setCenter(boardView);
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


}
