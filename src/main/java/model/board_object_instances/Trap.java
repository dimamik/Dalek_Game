package model.board_object_instances;

import enums.ObjectType;
import javafx.scene.paint.Color;
import model.BoardObject;
import model.Vector2D;

//TODO Remove this classes
public class Trap extends BoardObject {

    private final Vector2D singleMove = new Vector2D(0, 0);

    public Trap(Color color) {
        super(color);
        isMovable = true;
    }

    @Override
    public ObjectType getType() {
        return ObjectType.TRAP;
    }
}
