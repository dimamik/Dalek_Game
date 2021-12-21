package utils;

import com.google.inject.Inject;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.factories.CollisionActionFactory;
import model.object_action.CollisionReaction;
import java.util.Optional;

public record CollisionHandler(Board board, CollisionActionFactory collisionActionFactory) {
    @Inject
    public CollisionHandler {
    }

    public void handleCollision(BoardCell collisionCell) {

        BoardObject boardObject1 = collisionCell.getBoardObjects().get(0);
        BoardObject boardObject2 = collisionCell.getBoardObjects().get(1);

        Optional<CollisionReaction> objectAction = collisionActionFactory.getCollisionAction(boardObject1.getType(), boardObject2.getType());

        objectAction.ifPresent(action -> action.handleCollision(collisionCell));
    }
}
