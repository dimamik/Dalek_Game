package controller;

import enums.GameState;
import interfaces.EventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record GameStateController(AppController appController) implements EventListener<GameState> {

    @Override
    public void onEvent(GameState e) {
        if (e == GameState.DOCTOR_WON) {
            doctorWonAction();
        } else if (e == GameState.TELEPORT_GAINED) {
            teleportGainedAction();
        } else if (e == GameState.TIME_TRAVEL_GAINED) {
            timeTravelGained();
        } else if (e == GameState.GAME_ENDED) {
            gameEnded();
        }
    }

    private void doctorWonAction() {
        log.info("Doctor Won!");
        if (appController.campaignMode && appController.roundNumber < appController.MAX_ROUNDS) {
            log.info("NEXT_ROUND");
            appController.startCampaignGame();
        } else {
            log.info("Game Over");
            appController.endGame();
        }
    }

    private void teleportGainedAction() {
        log.info("TELEPORT GAINED");
        appController.Teleport.setText("TELEPORT: " + appController.gameUtil.teleportsNumber);
        appController.Teleport.setDisable(false);
    }

    private void timeTravelGained() {
        log.info("TIME_TRAVEL GAINED");
        appController.TimeTravel.setText("TIME TRAVEL: " + appController.gameUtil.timeTravelNumber);
        appController.TimeTravel.setDisable(false);
    }

    private void gameEnded() {
        log.info("GAME ENDED!");
        if (appController.gameUtil.timeTravelNumber == 0) {
            appController.endGame();
        } else {
            appController.infoLabel.setText("Use time travel!");
        }
    }
}