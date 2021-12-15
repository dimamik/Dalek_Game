package model.board_object_instances;

import enums.ObjectType;
import javafx.scene.paint.Color;
import model.BoardObject;

public class Heap extends BoardObject {

    public final static Color COLOR = Color.BLUE;

    public Heap() {
        super(COLOR, false);
    }

    @Override
    public ObjectType getType() {
        return ObjectType.HEAP;
    }
}
