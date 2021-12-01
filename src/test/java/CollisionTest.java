import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import model.board_object_instances.Cat;
import model.board_object_instances.Mouse;
import static org.junit.Assert.*;

import model.board_object_instances.Trap;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import utils.CollisionHandler;

import java.awt.*;

public class CollisionTest {

    @Test
    public void catCatCollisionTest() {
        // given
        Board board = new Board(10, 10);
        CollisionHandler collisionHandler = new CollisionHandler(board);
        BoardObject boardObject1 = new Cat(Color.BLACK);
        BoardObject boardObject2 = new Cat(Color.BLACK);
        BoardCell collisionCell = board.getBoardCells().get(0);
        collisionCell.getBoardObjects().add(boardObject1);
        collisionCell.getBoardObjects().add(boardObject2);

        // when
        collisionHandler.handleCollision(collisionCell);

        // then
        assertEquals(1, collisionCell.getBoardObjects().size());
    }

    @Test
    public void trapMouseCollisionTest() {
        // given
        Board board = new Board(10, 10);
        CollisionHandler collisionHandler = new CollisionHandler(board);
        BoardObject boardObject1 = new Trap(Color.RED);
        BoardObject boardObject2 = new Mouse(Color.GRAY);
        BoardCell collisionCell = board.getBoardCells().get(0);
        collisionCell.getBoardObjects().add(boardObject1);
        collisionCell.getBoardObjects().add(boardObject2);

        // when
        collisionHandler.handleCollision(collisionCell);

        // then
        assertEquals(Color.RED, collisionCell.getBoardObjects().get(0).getColor());
    }


}
