package utils;

import com.google.inject.Inject;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.factories.CollisionActionFactory;
import model.object_action.CollisionReaction;

import java.util.Optional;
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

        BoardObject boardObject1 = collisionCell.getBoardObjects().get(0);
        BoardObject boardObject2 = collisionCell.getBoardObjects().get(1);

        Optional<CollisionReaction> objectAction = CollisionActionFactory.getCollisionAction(boardObject1.getType(), boardObject2.getType());


//      TODO Should it use board?
//        If no then remove
        objectAction.ifPresent(action -> action.handleCollision(collisionCell));

    }
}
