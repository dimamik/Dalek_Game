package controller;


import enums.GameState;
import interfaces.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameStateController implements EventListener<GameState> {

    AppController appController;

    public GameStateController(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void onEvent(GameState e) {

        if (e == GameState.GAME_RUNNING) {
            appController.startGame();
            appController.setGameState(GameState.GAME_RUNNING);
        }

        if (e == GameState.GAME_ENDED) {
//            Check if we are playing round game or not
            appController.setGameState(e);
            appController.movementButtons.setDisable(true);
            appController.instructionsText.setText("Game over!");
        } else if (e == GameState.NEXT_ROUND) {
            appController.startNextRound();
            appController.setGameState(GameState.GAME_RUNNING);
            appController.movementButtons.setDisable(false);
            appController.instructionsText.setText("Next round!");
        }

    }
}