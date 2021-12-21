package model.object_action;

import model.BoardCell;
import model.board_object_instances.Heap;

public class DalekDalekReaction implements CollisionReaction {
    @Override
    public void handleCollision(BoardCell boardCell) {
        boardCell.clearBoardCell();
        boardCell.addBoardObject(new Heap());
    }
}
