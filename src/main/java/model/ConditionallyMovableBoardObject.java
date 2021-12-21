package model;

import javafx.scene.paint.Color;

public abstract class ConditionallyMovableBoardObject extends BoardObject {

    public ConditionallyMovableBoardObject(Color color) {
        super(color, true);
    }

    public abstract Vector2D getMove(Vector2D myPosition, Vector2D conditionPosition);
}
