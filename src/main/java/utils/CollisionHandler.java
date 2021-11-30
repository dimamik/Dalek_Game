package utils;

import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import model.board_object_instances.Cat;
import model.board_object_instances.Mouse;
import model.board_object_instances.Trap;

public class CollisionHandler {
    Board board;

    public CollisionHandler(Board board) {
        this.board = board;
    }



    public void handleCollision(BoardCell collisionCell) {

        BoardObject boardObject1 = collisionCell.getBoardObjects().get(0);
        BoardObject boardObject2 = collisionCell.getBoardObjects().get(1);

        // TODO implement logic of resolving collision
        if (boardObject1 instanceof Cat && boardObject2 instanceof Cat ||
            boardObject1 instanceof Mouse && boardObject2 instanceof Mouse)
            collisionCell.getBoardObjects().remove(1);
        else if (boardObject1 instanceof Trap && boardObject2 instanceof Cat ||
                 boardObject1 instanceof Trap && boardObject2 instanceof Mouse)
            collisionCell.getBoardObjects().remove(1);
        else
            collisionCell.getBoardObjects().remove(boardObject2);
    }
}
