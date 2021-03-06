package model.object_action;

import model.BoardCell;
import model.board_object_instances.DeadDoctor;

public class DoctorDalekReaction implements CollisionReaction {
    @Override
    public void handleCollision(BoardCell boardCell) {
        boardCell.clearBoardCell();
        boardCell.addBoardObject(new DeadDoctor());
    }
}
