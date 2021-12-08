package utils;

import com.google.inject.Inject;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;

public class PositionUtil {

    private final Board board;

    private final CollisionHandler collisionHandler;

    @Inject
    public PositionUtil(Board board, CollisionHandler collisionHandler) {
        this.board = board;
        this.collisionHandler = collisionHandler;
    }

    public void move(BoardCell cell) {

    }

    public void changePosition(BoardCell sourceCell, Vector2D shift) {

        if (!sourceCell.getBoardObjects().get(0).isMovable()) return;

        BoardCell targetCell = board.getBoardCell(sourceCell.getPosition().add(shift));

        targetCell.getBoardObjects().add(sourceCell.getBoardObjects().get(0));

        sourceCell.getTopBoardObject().ifPresent(
                sourceCell::removeBoardObject
        );

        if (targetCell.getBoardObjects().size() > 1) {
            collisionHandler.handleCollision(targetCell);
        }
    }

    public Board getBoard() {
        return board;
    }
}
