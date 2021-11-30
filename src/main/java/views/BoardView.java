package views;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BoardView extends Parent {

    private final BoardCellView[][] arrayOfCells;


    public BoardView() {
//        TODO @Bartek Wstrzyknąć skądś rozmiar planszy żeby wiedzieć jaki będzie rozmiar planszy w px
//        oraz jaki będzie rozmiar planszy w ilosci klatek x,y, też wszyknąć
//        Tu rysujemy ten kwadrat

//        TODO replace WIDTH,HEIGHT with some params coming from parent
        int WIDTH = 10;
        int HEIGHT = 10;
//        TODO Tu stworzyć listę BoardCellView kwadratową o zadanym rozmiarze klatek x * y

        arrayOfCells = new BoardCellView[WIDTH][HEIGHT];
        VBox rows = new VBox();
        for (int y = 0; y < HEIGHT; y++) {
            HBox row = new HBox();
            for (int x = 0; x < WIDTH; x++) {
                arrayOfCells[x][y] = new BoardCellView();
//                TODO Tu Ustawiamy reakcję na przycisk
//                int finalX = x;
//                int finalY = y;
//                arrayOfCells[x][y].setOnMouseClicked(
//                        event -> notifyAllListeners(arrayOfCells[finalX][finalY].getCurrentObject())
//                );
                row.getChildren().add(arrayOfCells[x][y]);
            }

            rows.getChildren().add(row);
        }
        getChildren().add(rows);


    }

    public BoardCellView getBoardCellView(int x, int y) {
//        TODO return boardCellView from the list of boardCellViews
        return new BoardCellView();
    }
}
