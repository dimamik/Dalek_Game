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

//        if (e == GameState.GAME_RUNNING) {
//            appController.startRandomGame();
//            appController.setGameState(GameState.GAME_RUNNING);
//        }
        if (e == GameState.DOCTOR_WON) {

            log.info("Doctor Won!");

            if (appController.isRoundGame && appController.roundNumber < appController.MAX_ROUNDS) {
                log.info("NEXT_ROUND");
                appController.startRoundGame();
            } else {
                log.info("Game Over");
                appController.endGame();
            }

        } else if (e == GameState.TELEPORT_GAINED) {
            log.info("TELEPORT GAINED");
            appController.Teleport.setText("TELEPORT: " + appController.gameUtil.teleportsNumber);

        } else if (e == GameState.TIME_TRAVEL_GAINED) {
            log.info("TIME_TRAVEL GAINED");
            appController.TimeTravel.setText("TIME TRAVEL: " + appController.gameUtil.timeTravelNumber);
        } else if (e == GameState.GAME_ENDED) {
            log.info("GAME ENDED!");
            if (appController.gameUtil.timeTravelNumber == 0) {
                appController.endGame();
            } else {
                appController.instructionsText.setText("You have time travel left, you can use it to go back in time and maybe win a game!");
            }
        }

    }
}