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
        appController.setGameState(e);
        appController.movementButtons.setDisable(true);
        appController.instructionsText.setText("Game over!");
    }


}
