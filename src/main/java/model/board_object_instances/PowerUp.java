package model.board_object_instances;

import javafx.scene.paint.Color;
import model.Board;
import model.BoardObject;


public abstract class PowerUp extends BoardObject {


    public PowerUp(Color color) {
        super(color, false);
    }

    public abstract void applyPowerUp(Board board, Doctor doctor);

}
