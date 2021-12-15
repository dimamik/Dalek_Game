package model.board_object_instances;

import enums.ObjectType;
import javafx.scene.paint.Color;
import model.MovableBoardObject;
import model.Vector2D;

//TODO Remove this classes
public class Cat extends MovableBoardObject {

    private final Vector2D singleMove = new Vector2D(2, 1);

    public Cat(Color color) {
        super(color);
        isMovable = true;
    }

    @Override
    public ObjectType getType() {
        return ObjectType.CAT;
    }

    public Vector2D getSingleMove() {
        return singleMove;
    }
}
