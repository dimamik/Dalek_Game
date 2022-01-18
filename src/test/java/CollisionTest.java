import enums.ObjectType;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import model.board_object_instances.Dalek;
import model.board_object_instances.Doctor;
import model.board_object_instances.Heap;
import model.factories.CollisionActionFactory;
import org.junit.jupiter.api.Test;
import utils.CollisionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
// TODO OUTDATED

public class CollisionTest {

    public BoardCell processCollision(BoardObject boardObject1, BoardObject boardObject2, int boardSize, int xV, int yV) {
        //given
        Board board = new Board(boardSize, boardSize);
        CollisionActionFactory collisionActionFactory = new CollisionActionFactory();
        CollisionHandler collisionHandler = new CollisionHandler(board, collisionActionFactory);
        BoardCell collisionCell = board.getBoardCell(new Vector2D(xV, yV));
        collisionCell.getBoardObjects().add(boardObject1);
        collisionCell.getBoardObjects().add(boardObject2);

        // when
        collisionHandler.handleCollision(collisionCell);

        return collisionCell;
    }

    @Test
    public void dalekDalekCollisionTest() {
        // given
        BoardObject dalek1 = new Dalek();
        BoardObject dalek2 = new Dalek();

        // when
        BoardCell collisionCell = processCollision(dalek1, dalek2, 10, 0, 0);

        //then
        assertEquals(1, collisionCell.getBoardObjects().size());
        assertEquals(ObjectType.HEAP, collisionCell.getBoardObjects().get(0).getType());
    }

    @Test
    public void dalekHeapCollisionTest() {
        // given
        BoardObject dalek = new Dalek();
        BoardObject heap = new Heap();

        // when
        BoardCell collisionCell = processCollision(dalek, heap, 10, 0, 0);

        //then
        assertEquals(1, collisionCell.getBoardObjects().size());
        assertEquals(ObjectType.HEAP, collisionCell.getBoardObjects().get(0).getType());
    }

    @Test
    public void dalekDoctorCollisionTest() {
        // given
        BoardObject dalek = new Dalek();
        BoardObject doctor = new Doctor();

        // when
        BoardCell collisionCell = processCollision(dalek, doctor, 10, 0, 0);

        //then
        assertEquals(1, collisionCell.getBoardObjects().size());
        assertEquals(ObjectType.HEAP, collisionCell.getBoardObjects().get(0).getType());
    }

    @Test
    public void doctorHeapCollisionTest() {
        // given
        BoardObject doctor = new Dalek();
        BoardObject heap = new Heap();

        // when
        BoardCell collisionCell = processCollision(doctor, heap, 10, 0, 0);

        //then
        assertEquals(1, collisionCell.getBoardObjects().size());
        assertEquals(ObjectType.HEAP, collisionCell.getBoardObjects().get(0).getType());
    }

}