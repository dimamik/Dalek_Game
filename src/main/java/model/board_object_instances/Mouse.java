package model.board_object_instances;

import model.BoardObject;
import model.Vector2D;

import java.awt.*;

public class Mouse extends BoardObject {

    private final Vector2D singleMove = new Vector2D(0, 1);

    public Mouse(Color color) {
        super(color);
    }

    @Override
    public void handleCollision() {

    }
}
