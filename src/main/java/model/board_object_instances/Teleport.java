package model.board_object_instances;

import enums.ObjectType;
import javafx.scene.paint.Color;
import model.BoardObject;

public class Teleport extends BoardObject {
    public Teleport() {
        super(Color.BLUE);
    }

    @Override
    public ObjectType getType() {
        return ObjectType.TELEPORT;
    }


}
