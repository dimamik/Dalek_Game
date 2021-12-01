package model;

import controller.StateController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class BoardCell {
    private final Vector2D position;
    private final ObservableList<BoardObject> boardObjects;

    public BoardCell(Vector2D position) {
        this.position = position;
        this.boardObjects = FXCollections.observableArrayList();
        addListenerToListChange();
    }

    public Vector2D getPosition() {
        return position;
    }

    public Optional<BoardObject> getBoardObject() {
        if (this.boardObjects.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(this.boardObjects.get(0));
    }

    public void addBoardObject(BoardObject boardObject) {
        boardObjects.add(boardObject);
    }

    public void removeBoardObject(BoardObject boardObject) {
        boardObjects.remove(boardObject);
    }


    public void addListenerToListChange() {
        boardObjects.addListener((ListChangeListener<BoardObject>) c -> {
            try {
                StateController.getInstance().ifPresent(stateController -> stateController.cellChangeOccurred(this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public List<BoardObject> getBoardObjects() {
        return this.boardObjects;
    }
}
