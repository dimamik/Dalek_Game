package views;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardCellView extends Parent {


    @Inject
    public BoardCellView(@Named("cellSize") int cellSize) {
        Rectangle rectangle = new Rectangle(cellSize, cellSize);
        rectangle.setFill(Color.LIGHTGRAY);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(0.2);
        getChildren().add(rectangle);

    }

    public void drawBoardObjectView(BoardObjectView boardObjectView) {
        int maxObjectsDisplayed = 2;
        if (getChildren().size() > maxObjectsDisplayed) {
            getChildren().remove(2);
        }
        getChildren().add(boardObjectView);
    }

    public void clearBoardObjectView() {
        while (getChildren().size() > 1) {
            getChildren().remove(1);
        }
    }
}
