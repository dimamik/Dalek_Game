package model;

import interfaces.EventEmitter;
import interfaces.EventListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.Optional;

public class BoardCell extends EventEmitter<BoardCell> {
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

    public Optional<BoardObject> getTopBoardObject() {
        if (this.boardObjects.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(this.boardObjects.get(0));
    }

    public ConditionallyMovableBoardObject getConditionallyMovableObject() {
        return (ConditionallyMovableBoardObject) this.boardObjects.get(0);
    }

    public void addBoardObject(BoardObject boardObject) {
        boardObjects.add(boardObject);
    }

    public void clearBoardCell() {
        boardObjects.clear();
    }

    public void removeBoardObject(BoardObject boardObject) {
        boardObjects.remove(boardObject);
    }


    public void addListenerToListChange() {
        boardObjects.addListener((ListChangeListener<BoardObject>) c -> {
            emit(this);
        });
    }

    public List<BoardObject> getBoardObjects() {
        return this.boardObjects;
    }

    public boolean isEmpty() {
        return this.boardObjects.isEmpty();
    }

    @Override
    public void addListener(EventListener<BoardCell> listener) {
        eventListeners.add(listener);
    }

    @Override
    public void removeListener(EventListener<BoardCell> listener) {
        eventListeners.remove(listener);
    }

    @Override
    public String toString() {
        return "BoardCell{" +
                "boardObjects=" + boardObjects +
                '}';
    }
}
