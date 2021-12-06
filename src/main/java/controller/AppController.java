package controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.GameModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import utils.GameUtil;
import views.BoardView;

/**
 * Is responsible for Controlling Board with its objects and movement keys
 * Later we will need to split this up into board controller and menu controller
 */
public class AppController {
    public GameUtil gameUtil;

    @FXML
    public VBox rightSide;

    @FXML
    public VBox movementButtons;

    @FXML
    public BoardView boardView;

    @FXML
    private void initialize() {
        new BoardController(boardView);
        Injector injector = Guice.createInjector(new GameModule());
        gameUtil = injector.getInstance(GameUtil.class);
        gameUtil.runGame();
    }

    public void onPress(ActionEvent actionEvent) {
//        TODO Implement button handling
//        When button from menu is pressed
        System.out.println("Button pressed!" + actionEvent);
    }
}
