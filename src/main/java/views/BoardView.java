package views;

import javafx.scene.shape.Rectangle;

public class BoardView extends Rectangle {

    public BoardView() {
        super(200, 200);
//        TODO Tu stworzyć listę BoardCellView kwadratową o zadanym rozmiarze klatek x * y
    }

    public BoardCellView getBoardCellView(int x, int y) {
//        TODO
        return new BoardCellView();
    }
}
