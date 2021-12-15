package model.object_action.outdated;

import model.BoardCell;
import model.object_action.CollisionReaction;

public class CatCatReaction implements CollisionReaction {
    @Override
    public void handleCollision(BoardCell boardCell) {
        System.out.println("Cat Cat Collision");
        boardCell.getBoardObjects().remove(0);
    }
}
