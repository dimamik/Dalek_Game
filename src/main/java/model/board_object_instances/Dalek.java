package model.board_object_instances;

import enums.ObjectType;
import javafx.scene.paint.Color;
import model.ConditionallyMovableBoardObject;
import model.Vector2D;

public class Dalek extends ConditionallyMovableBoardObject {

    public final static Color COLOR = Color.RED;

    public Dalek() {
        super(COLOR);
    }

    @Override
    public ObjectType getType() {
        return ObjectType.DALEK;
    }

    @Override
    public Vector2D getMove(Vector2D myPosition, Vector2D conditionPosition) {
//        TODO Implement logic of calculating the next move
        return new Vector2D(1, 1);
    }
}
