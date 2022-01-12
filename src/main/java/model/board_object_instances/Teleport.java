package model.board_object_instances;

import enums.ObjectType;
import javafx.scene.paint.Color;
import model.Board;

public class Teleport extends PowerUp {
    public Teleport() {
        super(Color.BLUE);
    }

    @Override
    public ObjectType getType() {
        return ObjectType.TELEPORT;
    }

    @Override
    public void applyPowerUp(Board board, Doctor doctor) {
//        TODO Apply teleport
    }
}
