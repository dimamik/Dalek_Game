package model.object_action;

import model.BoardCell;
import model.board_object_instances.Heap;

public class DoctorDalekReaction implements CollisionReaction {
    @Override
    public void handleCollision(BoardCell boardCell) {
        boardCell.clearBoardCell();
//        End of the game :)
        boardCell.addBoardObject(new Heap());
    }
}
