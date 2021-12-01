package utils;

import model.Board;
import model.BoardCell;
import model.Vector2D;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
        Vector2D targetPosition = sourceCell.getPosition().add(shift);
        BoardCell targetCell = board.getBoardCells().stream()
                .filter(cell -> cell.getPosition().equals(targetPosition))
                .collect(Collectors.toList())
                .get(0);

        targetCell.getBoardObjects().add(sourceCell.getBoardObjects().get(0));
        sourceCell.getBoardObjects().remove(0);
    }

    public Board getBoard() {
        return board;
    }
}
