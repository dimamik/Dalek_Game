package model.object_action;

import enums.ObjectType;
import model.BoardCell;

public class DoctorPowerUpReaction implements CollisionReaction {

    @Override
    public void handleCollision(BoardCell boardCell) {

// TODO: implement

        boardCell.getBoardObjects().forEach(boardObject -> {
            if (boardObject.getType() != ObjectType.DOCTOR) {
                boardCell.removeBoardObject(boardObject);
            }
        });


        System.out.println("DoctorPowerUpReaction");

    }
}
