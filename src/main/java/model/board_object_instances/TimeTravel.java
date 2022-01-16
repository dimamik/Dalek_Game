package model.board_object_instances;

import enums.ObjectType;
import javafx.scene.paint.Color;
import model.BoardObject;

public class TimeTravel extends BoardObject {
    public TimeTravel() {
        super(Color.YELLOW);
    }

    @Override
    public ObjectType getType() {
        return ObjectType.TIME_TRAVEL;
    }
}
