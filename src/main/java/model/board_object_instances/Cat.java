package model.board_object_instances;

import javafx.scene.paint.Color;
import model.BoardObject;
import model.Vector2D;


public class Cat extends BoardObject {

    private final Vector2D singleMove = new Vector2D(2, 1);

    public Cat(Color color) {
        super(color);
    }

    @Override
    public void handleCollision() {

    }

    public Vector2D getSingleMove() {
        return singleMove;
    }
}