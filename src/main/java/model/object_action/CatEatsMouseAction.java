package model.object_action;

import model.BoardCell;

public class CatEatsMouseAction implements ObjectAction {


    @Override
    public void handleCollision(BoardCell boardCell) {
        System.out.println("Cat eats mouse");
    }
}
