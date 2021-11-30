package model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;

public class BoardCell {
    private Vector2D position;

    public void setBoardObjects(ObservableList<BoardObject> boardObjects) {
        this.boardObjects = boardObjects;
    }

    private ObservableList<BoardObject> boardObjects;


    public BoardCell(Vector2D position) {
        this.position = position;
//        this.boardObjects = Collections.<BoardObject>emptyList();
        this.boardObjects = FXCollections.observableArrayList();

        addListenerToListChange();
//        boardObjects.add(new BoardObject());
    }

    public void addListenerToListChange() {
//        This method is called when list of cells is updated
        boardObjects.addListener((ListChangeListener<BoardObject>) c -> {
            System.out.println("change occurred!");
//                TODO Notify the controller with cellChangeOccurred

        });
    }


    public Vector2D getPosition() {
        return this.position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public List<BoardObject> getBoardObjects() {
        return this.boardObjects;
    }

    public void addBoardObject(BoardObject boardObject) {
        this.boardObjects.add(boardObject);
    }
}
