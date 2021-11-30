package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardObjectView extends Rectangle {

    //    TODO Remove from there
//    BoardObjectView is only representing having object!
    Color objectColor;

    //    TODO There BoardObjectView Can accept either props or Object
    public BoardObjectView(Color objectColor) {
        super(10, 10);
        this.objectColor = objectColor;
        setFill(Color.LIGHTGRAY);
    }

//    TODO Add Additional BoardObjectView props
//    such as possible moves or moving strategy
//    Energy or strength

}
