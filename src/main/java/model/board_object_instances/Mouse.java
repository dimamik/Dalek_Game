package model.board_object_instances;

import enums.ObjectType;
import javafx.scene.paint.Color;
import model.MovableBoardObject;
import model.Vector2D;

//TODO Remove this classes
public class Mouse extends MovableBoardObject {

    private final Vector2D singleMove = new Vector2D(0, 1);

    public Mouse(Color color) {
        super(color);
        isMovable = true;
    }

    @Override
    public ObjectType getType() {
        return ObjectType.MOUSE;
    }

    public Vector2D getSingleMove() {
        return singleMove;
    }
}
