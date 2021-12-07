package model.board_object_instances;

import enums.ObjectType;
import javafx.scene.paint.Color;
import model.BoardObject;
import model.Vector2D;

//TODO Remove this classes
public class Cat extends BoardObject {

    private final Vector2D singleMove = new Vector2D(2, 1);

    public Cat(Color color) {
        super(color);
    }

    @Override
    public ObjectType getType() {
        return ObjectType.CAT;
    }

//    @Override
//    public void handleCollision() {
//
//    }

    public Vector2D getSingleMove() {
        return singleMove;
    }
}
