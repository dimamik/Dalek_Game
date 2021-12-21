package model.object_action;

import model.BoardCell;
import model.board_object_instances.Heap;

public class DoctorDalekReaction implements CollisionReaction {
    @Override
    public void handleCollision(BoardCell boardCell) {
//        TODO What happens when the Doctor collides with a Dalek -> Doctor Looses
        boardCell.clearBoardCell();
        boardCell.addBoardObject(new Heap());
    }
}
