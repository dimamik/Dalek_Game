package model;

import javafx.scene.paint.Color;


// TODO May be this class is useless
public abstract class MovableBoardObject extends BoardObject {
    public MovableBoardObject(Color color) {
        super(color);
    }

    public abstract Vector2D getSingleMove();
}
