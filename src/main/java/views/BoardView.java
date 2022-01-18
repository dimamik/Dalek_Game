package views;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BoardView extends Parent {

    private final BoardCellView[][] arrayOfCells;

    @Inject
    public BoardView(@Named("cols") int cols, @Named("rows") int rows, @Named("cellSize") int cellSize) {
        arrayOfCells = new BoardCellView[cols][rows];
        this.initBoardCellViews(cols, rows, cellSize);
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
