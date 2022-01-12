package model.board_object_instances;

import enums.ObjectType;
import javafx.scene.paint.Color;
import model.Board;

public class TimeTravel extends PowerUp {
    public TimeTravel() {
        super(Color.YELLOW);
    }

    @Override
    public ObjectType getType() {
        return ObjectType.TIME_TRAVEL;
    }

    @Override
    public void applyPowerUp(Board board, Doctor doctor) {
//    TODO Apply time travel logic

    }

}
