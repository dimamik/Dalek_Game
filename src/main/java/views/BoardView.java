package views;

import javafx.scene.shape.Rectangle;

public class BoardView extends Rectangle {

    public BoardView() {
//        TODO Wstrzyknąć board params żeby wiedzieć jaki będzie rozmiar planszy w px
//        oraz jaki będzie rozmiar planszy w ilosci klatek x,y
        super(400, 400);

//        TODO replace x,y with some params coming from parent
        int x = 10;
        int y = 10;
//        TODO Tu stworzyć listę BoardCellView kwadratową o zadanym rozmiarze klatek x * y

    }

    public BoardCellView getBoardCellView(int x, int y) {
//        TODO return boardCellView from the list of boardCellViews
        return new BoardCellView();
    }
}
