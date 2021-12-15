package model.object_action.outdated;

import javafx.scene.paint.Color;
import model.BoardCell;
import model.object_action.CollisionReaction;

public class MouseTrapReaction implements CollisionReaction {
    @Override
    public void handleCollision(BoardCell boardCell) {
        System.out.println("Mouse Trap Collision");
        boardCell.getBoardObjects().remove(0);
        boardCell.getBoardObjects().get(0).setColor(Color.BLUE);
    }
}
