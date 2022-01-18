package views;

import com.google.inject.Inject;
import javafx.scene.shape.Rectangle;
import model.BoardObject;

public class BoardObjectView extends Rectangle {

    @Inject
    public BoardObjectView(BoardObject boardObject) {
        super(20, 20);
        setTranslateX(15);
        setTranslateY(15);
        setFill(boardObject.getColor());
    }
}
