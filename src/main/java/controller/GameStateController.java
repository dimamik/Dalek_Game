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
            appController.startRandomGame();
            appController.setGameState(GameState.GAME_RUNNING);
        }

        if (e == GameState.GAME_ENDED) {
            appController.endGame();

        } else if (e == GameState.NEXT_ROUND) {
            appController.startRoundGame();
            appController.setGameState(GameState.GAME_RUNNING);
            appController.movementButtons.setDisable(false);
            appController.instructionsText.setText("Next round!");
        } else if (e == GameState.TELEPORT_GAINED) {
            System.out.println("TELEPORT_GAINED");
            appController.Teleport.setText("TELEPORT: " + appController.gameUtil.teleportsNumber);

        } else if (e == GameState.TIME_TRAVEL_GAINED) {
            appController.Teleport.setText("TIME TRAVEL: " + appController.gameUtil.timeTravelNumber);
        }

    }
}