package utils;

import com.google.inject.Inject;
import enums.Direction;
import enums.GameState;
import enums.ObjectType;
import interfaces.EventEmitter;
import lombok.extern.slf4j.Slf4j;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class PositionUtil extends EventEmitter<GameState> {

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
        LinkedList<BoardObject> movableObjects = new LinkedList<>();
        sourceCell.getBoardObjects().stream().filter(BoardObject::isMovable).forEach(movableObjects::add);
        if (movableObjects.size() != 1) {
            throw new RuntimeException("There are two movable objects on the cell");
        }
        BoardObject objectToMove = movableObjects.getFirst();
        BoardCell targetCell = board.getBoardCell(sourceCell.getPosition().add(shift));
        checkForTeleportAndTimeTravel(objectToMove, targetCell);
        targetCell.getBoardObjects().add(objectToMove);
        sourceCell.removeBoardObject(objectToMove);
        if (targetCell.getBoardObjects().size() > 1) {
            collisionHandler.handleCollision(targetCell);
        }
        return targetCell;
    }

    private void checkForTeleportAndTimeTravel(BoardObject objectToMove, BoardCell targetCell) {
        if (objectToMove.getType() != ObjectType.DOCTOR) {
            return;
        }
        targetCell.getBoardObjects().stream().filter(boardObject -> boardObject.getType() == ObjectType.TELEPORT || boardObject.getType() == ObjectType.TIME_TRAVEL)
                .forEach(boardObject ->
                        emit(boardObject.getType() == ObjectType.TIME_TRAVEL ? GameState.TIME_TRAVEL_GAINED : GameState.TELEPORT_GAINED)
                );
    }


    public void moveAllDaleks(Vector2D doctorPosition, List<BoardCell> occupiedCells) {
        List<BoardCell> daleksCells =
                occupiedCells.stream().filter(boardCell -> boardCell.getConditionallyMovableObject().isPresent()).toList();
        for (BoardCell dalekCell : daleksCells) {
            Vector2D currentPosition = dalekCell.getPosition();
            if (dalekCell.getConditionallyMovableObject().isPresent()) {
                Vector2D singleMove = dalekCell.getConditionallyMovableObject().get().getMove(currentPosition, doctorPosition);
                if (this.isMovePossible(board.getBoardCell(currentPosition), singleMove)) {
                    BoardCell targetCell = this.changePosition(board.getBoardCell(currentPosition), singleMove);
                    balanceOccupiedCells(occupiedCells, board.getBoardCell(currentPosition), targetCell);
                }
            }
        }
    }

    private void balanceOccupiedCells(List<BoardCell> occupiedCells, BoardCell sourceCell, BoardCell targetCell) {
        if (sourceCell.getBoardObjects().size() == 1) {
            occupiedCells.remove(sourceCell);
        }
        if (!occupiedCells.contains(targetCell)) {
            occupiedCells.add(targetCell);
        }
    }

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
    }

    public boolean isGameEnded(List<BoardCell> occupiedCells, BoardCell doctorCell) {
        if (!doctorExists(doctorCell)) {
            log.info("YOU LOOSE!");
            return true;
        } else if (!atLeastOneDalekExists(occupiedCells)) {
            log.info("YOU WIN!");
            return true;
        }
        return false;
    }

    public Vector2D getDirection(String directionString) {
        Direction direction = Direction.valueOf(directionString);
        return direction.toUnitVector();
    }

    public Board getBoard() {
        return board;
    }
}
