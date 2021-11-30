package views;

import com.google.inject.Guice;
import com.google.inject.Inject;
import guice.GameModule;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.BoardParams;

public class BoardView extends Parent {

    private final BoardCellView[][] arrayOfCells;


    @Inject
    public BoardView() {
//        TODO @Bartek Wstrzyknąć skądś rozmiar planszy żeby wiedzieć jaki będzie rozmiar planszy w px
//        oraz jaki będzie rozmiar planszy w ilosci klatek x,y, też wszyknąć
//        Tu rysujemy ten kwadrat

        BoardParams boardParams = Guice.createInjector(new GameModule()).getInstance(BoardParams.class);
//        TODO replace x_number,y_number with some params coming from parent
        int x_number = boardParams.getColsNo();
        int y_number = boardParams.getRowsNo();
        int cellSize = boardParams.getCellSize();
//        TODO Tu stworzyć listę BoardCellView kwadratową o zadanym rozmiarze klatek x * y

        arrayOfCells = new BoardCellView[x_number][y_number];
        VBox rows = new VBox();
        for (int y = 0; y < y_number; y++) {
            HBox row = new HBox();
            for (int x = 0; x < x_number; x++) {
                arrayOfCells[x][y] = new BoardCellView(cellSize);
//                arrayOfCells[x][y] = Guice.createInjector(new GameModule()).getInstance(BoardCellView.class);
                arrayOfCells[x][y].setOnMouseClicked(event -> {
//                    TODO @Bartek Tu wstrzyknąć kontroller i
//                     wywołać metodę z AppController. Albo podpiąć się listenerem
//                    Uwaga, tu kontroller odpowiada za masę rzeczy, co nie jest dobre
//                    Dobrym pomysłem będzie rozdzielenie kontrolera na kilka, ale później
//                    AppController.onCellChosen(arrayOfCells[x][y])
                });
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
