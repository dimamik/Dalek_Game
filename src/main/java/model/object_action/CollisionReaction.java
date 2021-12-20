package model.object_action;

import model.BoardCell;

public interface CollisionReaction {
    void handleCollision(BoardCell boardCell);
}
