package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardCellView extends Rectangle {

    public BoardCellView() {
//       Done There we need to draw cell
        super(10, 10);
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
        setStrokeWidth(0.2);
    }

    public void drawBoardObjectView(BoardObjectView boardObjectView) {
//        TODO Draw board object view
    }
}
