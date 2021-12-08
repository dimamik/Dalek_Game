package model.object_action;

import model.BoardCell;

public class CatMouseReaction implements CollisionReaction {

    @Override
    public void handleCollision(BoardCell boardCell) {
        System.out.println("Cat Mouse Collision");
    }
}
