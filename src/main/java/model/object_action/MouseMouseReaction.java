package model.object_action;

import model.BoardCell;

public class MouseMouseReaction implements CollisionReaction {
    @Override
    public void handleCollision(BoardCell boardCell) {
        System.out.println("Mouse Mouse Collision");
    }
}
