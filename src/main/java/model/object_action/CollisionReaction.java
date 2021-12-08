package model.object_action;

import model.BoardCell;

public interface CollisionReaction {

    //    Handles collision based on enums, but concrete type of handling!
    void handleCollision(BoardCell boardCell);

}
