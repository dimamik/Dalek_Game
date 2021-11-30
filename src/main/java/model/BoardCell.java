package model;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BoardCell {
    private Vector2D position;

    private ObservableList<BoardObject> boardObjects;

    public void setBoardObjects(ObservableList<BoardObject> boardObjects) {
        this.boardObjects = boardObjects;
    }



    public BoardCell(Vector2D position) {
        this.position = position;
//        this.boardObjects = Collections.<BoardObject>emptyList();
        this.boardObjects = FXCollections.observableArrayList();
        something();
        boardObjects.add(new BoardObject());
    }

//    public ObservableList<BoardObject> getBoardObjectsObservable(){
//        return FXCollections.observableList(this.boardObjects);
//    }

    public void something(){
        boardObjects.addListener((ListChangeListener<BoardObject>) c -> {
            System.out.println("change occured!");
//                TODO Controller Notification
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
