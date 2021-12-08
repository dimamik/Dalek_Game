package model.object_action;

import javafx.scene.paint.Color;
import model.BoardCell;

public class MouseTrapReaction implements CollisionReaction {
    @Override
    public void handleCollision(BoardCell boardCell) {
        System.out.println("Mouse Trap Collision");
        boardCell.getBoardObjects().remove(0);
        boardCell.getBoardObjects().get(0).setColor(Color.BLUE);
    }
}
