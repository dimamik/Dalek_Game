package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardObjectView extends Rectangle {

    Color objectColor;

//    TODO There BoardObjectView Can accept either props or Object
    public BoardObjectView(Color objectColor) {
        this.objectColor = objectColor;
        setFill(Color.LIGHTGRAY);
    }

//    TODO Add Additional BoardObjectView props
//    such as possible moves or moving strategy
//    Energy or strength


}
