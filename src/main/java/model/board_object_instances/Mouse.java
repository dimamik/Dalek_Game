package model.board_object_instances;

import javafx.scene.paint.Color;
import model.BoardObject;
import model.Vector2D;

//TODO Remove this classes
public class Mouse extends BoardObject {

    private final Vector2D singleMove = new Vector2D(0, 1);

    public Mouse(Color color) {
        super(color);
    }


}
