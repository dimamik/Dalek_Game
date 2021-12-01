package model.board_object_instances;

import javafx.scene.paint.Color;
import model.BoardObject;
import model.Vector2D;


public class Trap extends BoardObject {

    private final Vector2D singleMove = new Vector2D(0, 0);

    public Trap(Color color) {
        super(color);
    }

    @Override
    public void handleCollision() {

    }
}
