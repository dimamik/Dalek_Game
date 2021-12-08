package model.object_action;

import model.BoardCell;

public class CatCatReaction implements CollisionReaction {
    @Override
    public void handleCollision(BoardCell boardCell) {
        System.out.println("Cat Cat Collision");
        boardCell.getBoardObjects().remove(0);
    }
}
