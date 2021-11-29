package model;

import java.util.Collections;
import java.util.List;

public class BoardCell {
    private Vector2D position;
    private List<BoardObject> boardObjects;

    public BoardCell(Vector2D position) {
        this.position = position;
        this.boardObjects = Collections.<BoardObject>emptyList();
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
