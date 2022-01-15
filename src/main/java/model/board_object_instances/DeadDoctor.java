package model.board_object_instances;

import enums.ObjectType;
import javafx.scene.paint.Color;
import model.BoardObject;

public class DeadDoctor extends BoardObject {
    public DeadDoctor() {
        super(Color.CORAL, false);
    }

    @Override
    public ObjectType getType() {
        return ObjectType.DEAD_DOCTOR;
    }
}
