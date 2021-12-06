package views;

import com.google.inject.Inject;
import javafx.scene.shape.Rectangle;
import model.BoardObject;

public class BoardObjectView extends Rectangle {

    @Inject
    public BoardObjectView(BoardObject boardObject) {
//        TODO Change Hardcoded values to injection
//        When divided into smaller Controllers
        super(20, 20);
//        Place it in the middle of a cell
        setTranslateX(15);
        setTranslateY(15);
        setFill(boardObject.getColor());
    }

//    TODO Add Additional BoardObjectView props

}
