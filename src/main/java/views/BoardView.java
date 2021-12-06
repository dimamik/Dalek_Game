package views;

import com.google.inject.Guice;
import controller.BoardController;
import guice.GameModule;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.BoardParams;

public class BoardView extends Parent {

    private final BoardCellView[][] arrayOfCells;

    public BoardView() {
        BoardParams boardParams = Guice.createInjector(new GameModule()).getInstance(BoardParams.class);
        int x_number = boardParams.cols();
        int y_number = boardParams.rows();
        int cellSize = boardParams.cellSize();

        arrayOfCells = new BoardCellView[x_number][y_number];
        VBox rows = new VBox();
        for (int y = 0; y < y_number; y++) {
            HBox row = new HBox();
            for (int x = 0; x < x_number; x++) {
                arrayOfCells[x][y] = new BoardCellView(cellSize);
                int finalX = x;
                int finalY = y;
                arrayOfCells[x][y].setOnMouseClicked(event ->
                        BoardController.getInstance()
                                .ifPresent(boardController -> boardController.onCellChosen(finalX, finalY)));
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
