package utils;

import model.Board;
import model.BoardCell;
import model.Vector2D;

public class PositionUtil {

    private Board board;

    private CollisionHandler collisionHandler;

    public PositionUtil(Board board) {
        this.board = board;
        //TODO Wstrzykiwanie
        this.collisionHandler = new CollisionHandler(board);
    }

    public void changePosition(BoardCell sourceCell, Vector2D shift) {

        // find destinationCell
        BoardCell targetCell = board.getBoardCell(sourceCell.getPosition().add(shift));

        targetCell.getBoardObjects().add(sourceCell.getBoardObjects().get(0));
        sourceCell.getBoardObjects().remove(0);
    }

    public Board getBoard() {
        return board;
    }
}
