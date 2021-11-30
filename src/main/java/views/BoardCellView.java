package views;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardCellView extends Parent {

    @Inject
    public BoardCellView(@Named("cellSize") int cellSize) {
//       Done There we need to draw cell
        Rectangle rectangle = new Rectangle(cellSize, cellSize);
        rectangle.setFill(Color.LIGHTGRAY);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(0.2);
        getChildren().add(rectangle);
    }

    public void drawBoardObjectView(BoardObjectView boardObjectView) {
        if (getChildren().size() > 2) {
            getChildren().remove(2);
        }
        getChildren().add(boardObjectView);
    }
}
