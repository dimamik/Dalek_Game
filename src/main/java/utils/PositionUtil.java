package utils;

import com.google.inject.Inject;
import enums.Direction;
import enums.ObjectType;
import javafx.event.ActionEvent;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;

import javax.swing.text.html.ListView;
import java.util.ArrayList;
import java.util.List;

public class PositionUtil {

    private final Board board;

    private final CollisionHandler collisionHandler;

    @Inject
    public PositionUtil(Board board, CollisionHandler collisionHandler) {
        this.board = board;
        this.collisionHandler = collisionHandler;
    }

    public BoardCell move(BoardCell sourceCell, Vector2D direction) {
        if (this.isMovePossible(sourceCell, direction)) {
            return this.changePosition(sourceCell, direction);
        }
        return sourceCell;
    }

    private boolean isMovePossible(BoardCell boardCell, Vector2D direction) {
        Vector2D vector2D = boardCell.getPosition();
        vector2D = vector2D.add(direction);
        return vector2D.x() >= 0 && vector2D.x() < board.getCols() && vector2D.y() >= 0 && vector2D.y() < board.getRows();
    }

    public BoardCell changePosition(BoardCell sourceCell, Vector2D shift) {

        if (!sourceCell.getBoardObjects().get(0).isMovable()) return null;
        BoardCell targetCell = board.getBoardCell(sourceCell.getPosition().add(shift));
        targetCell.getBoardObjects().add(sourceCell.getBoardObjects().get(0));

        sourceCell.getTopBoardObject().ifPresent( sourceCell::removeBoardObject );

        if (targetCell.getBoardObjects().size() > 1) {
            collisionHandler.handleCollision(targetCell);
        }

        return targetCell;
    }

    public void moveAllDaleks(Vector2D doctorPosition, List<BoardCell> occupiedCells) {

        for (int i = 0; i < occupiedCells.size(); i++) {

            if (occupiedCells.get(i).getTopBoardObject().get().getType() == ObjectType.DALEK) {
                Vector2D currentPosition = occupiedCells.get(i).getPosition();
                Vector2D singleMove = findSingleDalekMove(doctorPosition, currentPosition);

                if (this.isMovePossible(board.getBoardCell(currentPosition), singleMove)) {
                    BoardCell targetCell = this.changePosition(board.getBoardCell(currentPosition), singleMove);
                    occupiedCells.set(i, targetCell);
                }
            }
        }
    }

    public Vector2D findSingleDalekMove(Vector2D doctorPosition, Vector2D dalekPosition) {
        if (doctorPosition.x() > dalekPosition.x() && doctorPosition.y() < dalekPosition.y()) {
            return Direction.NE.toUnitVector();
        }
        else if (doctorPosition.x() == dalekPosition.x() && doctorPosition.y() < dalekPosition.y()) {
            return Direction.N.toUnitVector();
        }
        else if (doctorPosition.x() < dalekPosition.x() && doctorPosition.y() < dalekPosition.y()) {
            return Direction.NW.toUnitVector();
        }
        else if (doctorPosition.x() > dalekPosition.x() && doctorPosition.y() == dalekPosition.y()) {
            return Direction.E.toUnitVector();
        }
        else if (doctorPosition.x() < dalekPosition.x() && doctorPosition.y() == dalekPosition.y()) {
            return Direction.W.toUnitVector();
        }
        else if (doctorPosition.x() > dalekPosition.x() && doctorPosition.y() > dalekPosition.y()) {
            return Direction.SE.toUnitVector();
        }
        else if (doctorPosition.x() == dalekPosition.x() && doctorPosition.y() > dalekPosition.y()) {
            return Direction.S.toUnitVector();
        }
        else {
            return Direction.SW.toUnitVector();
        }
    }


//    public Vector2D getBoardObjectPosition(BoardObject boardObject) {
//        for (int i = 0; i < board.getCols(); i++) {
//            for (int j = 0; j < board.getRows(); j++) {
//                if (board.getBoardCell(new Vector2D(i, j)).getBoardObjects().contains(boardObject)) {
//                    return new Vector2D(i, j);
//                }
//            }
//        }
//        return null;
//    }

    public boolean atLeastOneDalekExists(List<BoardCell> occupiedCells) {
        for (BoardCell cell : occupiedCells) {
            if (cell.getBoardObjects().get(0).getType() == ObjectType.DALEK) {
                return true;
            }
        }
        return false;
    }


    public boolean doctorExists(BoardCell doctorCell) {
        return doctorCell.getBoardObjects().get(0).getType() == ObjectType.DOCTOR;
//        for (int i = 0; i < board.getCols(); i++) {
//            for (int j = 0; j < board.getRows(); j++) {
//                if(board.getBoardCell(new Vector2D(i, j)).getTopBoardObject().isPresent()) {
//                    int objectType = board.getBoardCell(new Vector2D(i, j)).getTopBoardObject().get().getType().getObjectType();
//                    if (objectType == 2) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
    }

    public boolean isGameEnded(List<BoardCell> occupiedCells, BoardCell doctorCell) {
        if (!doctorExists(doctorCell)) {
            System.out.println("YOU LOOSE!");
            return true;
        } else if (!atLeastOneDalekExists(occupiedCells)) {
            System.out.println("YOU WIN!");
            return true;
        }
        return false;
    }

    public Vector2D getDirection(ActionEvent actionEvent) {
//        TODO Change this to get Vector2D from the actionEvent somewhere higher
        String eventTarget =  actionEvent.getTarget().toString();
        Direction direction = Direction.valueOf(eventTarget.substring(eventTarget.indexOf("'") + 1, eventTarget.lastIndexOf("'")));
        return direction.toUnitVector();
    }

    public Board getBoard() {
        return board;
    }
}
