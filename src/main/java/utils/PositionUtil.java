package utils;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;

import java.util.Optional;

public class PositionUtil {

    private final Board board;

    private final CollisionHandler collisionHandler;

    @Inject
    public PositionUtil(Board board, CollisionHandler collisionHandler) {
        this.board = board;
        this.collisionHandler = collisionHandler;
    }

    public void move(BoardCell boardCell, Vector2D direction) {
        if (this.isMovePossible(boardCell, direction)) {
            this.changePosition(boardCell, direction);
        }
    }

    private boolean isMovePossible(BoardCell boardCell, Vector2D direction) {
        Vector2D vector2D = boardCell.getPosition();
        vector2D = vector2D.add(direction);
        return vector2D.x() >= 0 && vector2D.x() < board.getCols() && vector2D.y() >= 0 && vector2D.y() < board.getRows();
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

    public Vector2D getBoardObjectPosition(BoardObject boardObject) {
        for (int i = 0; i < board.getCols(); i++) {
            for (int j = 0; j < board.getRows(); j++) {
                if (board.getBoardCell(new Vector2D(i, j)).getBoardObjects().contains(boardObject)) {
                    return new Vector2D(i, j);
                }
            }
        }
        return null;
    }

    public boolean atLeastOneDalekExists() {
        for (int i = 0; i < board.getCols(); i++) {
            for (int j = 0; j < board.getRows(); j++) {
                if(board.getBoardCell(new Vector2D(i, j)).getTopBoardObject().isPresent()) {
                    int objectType = board.getBoardCell(new Vector2D(i, j)).getTopBoardObject().get().getType().getObjectCode();
                    return objectType == 1;
                }
            }
        }
        return false;
    }

    public boolean doctorExists() {
        for (int i = 0; i < board.getCols(); i++) {
            for (int j = 0; j < board.getRows(); j++) {
                if(board.getBoardCell(new Vector2D(i, j)).getTopBoardObject().isPresent()) {
                    int objectType = board.getBoardCell(new Vector2D(i, j)).getTopBoardObject().get().getType().getObjectCode();
                    if (objectType == 2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isGameEnded() {
        if (!doctorExists()) {
            System.out.println("YOU LOOSE!");
            return true;
        } else if (!atLeastOneDalekExists()) {
            System.out.println("YOU WIN!");
            return true;
        }
        return false;
    }

    public Vector2D getDirection(ActionEvent actionEvent) {
        String eventTarget =  actionEvent.getTarget().toString();
        return this.getPosition(eventTarget.substring(eventTarget.indexOf("'") + 1, eventTarget.lastIndexOf("'")));
    }

    public Vector2D getPosition(String direction){
        switch (direction) {
            case "N" -> {
                return new Vector2D(0, -1);
            }
            case "NE" -> {
                return  new Vector2D(1, -1);
            }
            case "E" -> {
                return new Vector2D(1, 0);
            }
            case "SE" -> {
                return new Vector2D(1, 1);
            }
            case "S" -> {
                return new Vector2D(0, 1);
            }
            case "SW" -> {
                return new Vector2D(-1, 1);
            }
            case "W" -> {
                return new Vector2D(-1, 0);
            }
            case "NW" -> {
                return new Vector2D(-1, -1);
            }
            default -> {
                return null;
            }
        }
    }

    public Board getBoard() {
        return board;
    }
}
