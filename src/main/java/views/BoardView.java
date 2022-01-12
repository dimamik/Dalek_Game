package views;

import com.google.inject.Inject;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BoardView extends Parent {

    private final BoardCellView[][] arrayOfCells;

    @Inject
    public BoardView() {
//        TODO Inject this values to be consistent with the model
        int x_number = 10;
        int y_number = 10;
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
