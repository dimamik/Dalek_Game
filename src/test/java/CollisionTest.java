import javafx.scene.paint.Color;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import model.board_object_instances.Cat;
import model.board_object_instances.Mouse;
import model.board_object_instances.Trap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.CollisionHandler;

import static org.junit.Assert.assertEquals;

public class CollisionTest {

//    TODO Add Mock Objects, don't change model!

    @Test
    public void catCatCollisionTest() {
        // given
        Board board = new Board(10, 10);
        CollisionHandler collisionHandler = new CollisionHandler(board);
        BoardObject boardObject1 = new Cat(Color.BLACK);
        BoardObject boardObject2 = new Cat(Color.BLACK);
        BoardCell collisionCell = board.getBoardCell(new Vector2D(0, 0));
        collisionCell.getBoardObjects().add(boardObject1);
        collisionCell.getBoardObjects().add(boardObject2);

        // when
        collisionHandler.handleCollision(collisionCell);

        // then
        Assertions.assertEquals(1, collisionCell.getBoardObjects().size());
    }

    @Test
    public void trapMouseCollisionTest() {
        // given
        Board board = new Board(10, 10);
        CollisionHandler collisionHandler = new CollisionHandler(board);
        BoardObject boardObject1 = new Trap(Color.RED);
        BoardObject boardObject2 = new Mouse(Color.GRAY);
        BoardCell collisionCell = board.getBoardCell(new Vector2D(0, 0));
        collisionCell.getBoardObjects().add(boardObject1);
        collisionCell.getBoardObjects().add(boardObject2);

        // when
        collisionHandler.handleCollision(collisionCell);

        // then
        assertEquals(Color.RED, collisionCell.getBoardObjects().get(0).getColor());
    }

}
