package model.board_object_instances;

import enums.ObjectType;
import javafx.scene.paint.Color;
import model.BoardObject;

public class Doctor extends BoardObject {

    public final static Color COLOR = Color.BLACK;

    public Doctor() {
        super(COLOR, true);
    }

    @Override
    public ObjectType getType() {
        return ObjectType.DOCTOR;
    }
}
