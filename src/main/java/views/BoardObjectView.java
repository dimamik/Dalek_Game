package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardObjectView extends Rectangle {

//    TODO Remove from there
//    BoardObjectView is only displaying object
//    All logic is in BoardObject
    Color objectColor;

    //    TODO There BoardObjectView Can accept either props or Object
    public BoardObjectView(Color objectColor) {
        super(10, 10);
        this.objectColor = objectColor;
        setFill(objectColor);
    }

//    TODO Add Additional BoardObjectView props

}
