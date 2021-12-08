package model;

import javafx.scene.paint.Color;

public abstract class MovableBoardObject extends BoardObject {
    public MovableBoardObject(Color color) {
        super(color);
    }

    public abstract Vector2D getSingleMove();
}
