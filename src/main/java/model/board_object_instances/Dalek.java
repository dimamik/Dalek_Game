package model.board_object_instances;

import enums.Direction;
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
    public Vector2D getMove(Vector2D dalekPosition, Vector2D doctorPosition) {
        Vector2D bestMove = null;
        double distance = calculateDistance(dalekPosition, doctorPosition);
        for (Direction direction : Direction.values()) {
            Vector2D newPosition = dalekPosition.add(direction.toUnitVector());
            double newDistance = calculateDistance(newPosition, doctorPosition);
            if (newDistance < distance) {
                bestMove = direction.toUnitVector();
                distance = newDistance;
            }
        }
        return bestMove;
    }

    private double calculateDistance(Vector2D first, Vector2D second) {
        return Math.sqrt(Math.pow(first.x() - second.x(), 2) +
                Math.pow(first.y() - second.y(), 2));
    }
}
