package model.object_action;

import model.BoardCell;

public class CatTrapReaction implements CollisionReaction {
    @Override
    public void handleCollision(BoardCell boardCell) {
        System.out.println("Cat Trap Collision");
    }
}
