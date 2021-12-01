package views;

import javafx.scene.shape.Rectangle;
import model.BoardObject;

public class BoardObjectView extends Rectangle {

    //    TODO Place it in the middle of a cell
    public BoardObjectView(BoardObject boardObject) {
        super(10, 10);
        setFill(boardObject.getColor());
    }

//    TODO Add Additional BoardObjectView props

}
