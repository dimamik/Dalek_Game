package utils;

import com.google.inject.Inject;
import model.Board;
import model.BoardCell;
//import model.model.board_object_instances.Cat;
//import model.model.board_object_instances.Mouse;
//import model.model.board_object_instances.Trap;

public class CollisionHandler {
    private final Board board;

    @Inject
    public CollisionHandler(Board board) {
        this.board = board;
    }

    public void handleCollision(BoardCell collisionCell) {
//        // TODO implement logic of resolving collision
//        BoardObject boardObject1 = collisionCell.getBoardObjects().get(0);
//        BoardObject boardObject2 = collisionCell.getBoardObjects().get(1);
//
//        if (boardObject1 instanceof Cat && boardObject2 instanceof Cat ||
//                boardObject1 instanceof Mouse && boardObject2 instanceof Mouse) {
//            collisionCell.getBoardObjects().remove(1);
//        }
//        else if (boardObject1 instanceof Trap && boardObject2 instanceof Cat ||
//                boardObject1 instanceof Trap && boardObject2 instanceof Mouse) {
//            collisionCell.getBoardObjects().remove(1);
//        }
//        else {
//            collisionCell.getBoardObjects().remove(boardObject2);
//        }
    }

    private boolean collisionOccurred(BoardCell boardCell) {
        return boardCell.getBoardObjects().size() >= 2;
    }
}
