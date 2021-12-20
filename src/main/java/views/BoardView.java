package views;

import com.google.inject.Inject;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BoardView extends Parent {

    private final BoardCellView[][] arrayOfCells;

    @Inject
    public BoardView() {
        int x_number = 20;
        int y_number = 20;
        int cellSize = 50;
        arrayOfCells = new BoardCellView[x_number][y_number];
        this.initBoardCellViews(x_number, y_number, cellSize);
    }

    private void initBoardCellViews(int x_number, int y_number, int cellSize) {
        VBox rows = new VBox();
        for (int y = 0; y < y_number; y++) {
            HBox row = new HBox();
            for (int x = 0; x < x_number; x++) {
                arrayOfCells[x][y] = new BoardCellView(cellSize);
//                TODO Add Mouse Clicked Event Handling
//                int finalX = x;
//                int finalY = y;
//                arrayOfCells[x][y].setOnMouseClicked(event ->
//                        ));
                row.getChildren().add(arrayOfCells[x][y]);
            }
            rows.getChildren().add(row);
        }
        getChildren().add(rows);
    }

    public BoardCellView getBoardCellView(int x, int y) {
        return arrayOfCells[x][y];
    }
}
