import javafx.scene.paint.Color;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import model.board_object_instances.Cat;
import model.board_object_instances.Mouse;
import model.board_object_instances.Trap;
import model.factories.CollisionActionFactory;
import org.junit.jupiter.api.Test;
import utils.CollisionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollisionTest {


//    TODO Add Mock Objects, don't change model!

    public BoardCell processCollision(BoardObject boardObject1, BoardObject boardObject2, int boardSize, int xV, int yV) {
        //given
        Board board = new Board(boardSize, boardSize);
//        TODO Warning, this was a singleton!
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
    public void catCatCollisionTest() {
        // given
        Cat boardObject1 = new Cat(Color.RED);
        Cat boardObject2 = new Cat(Color.RED);

        // when
        BoardCell collisionCell = processCollision(boardObject1, boardObject2, 10, 0, 0);

        //then
        assertEquals(1, collisionCell.getBoardObjects().size());
    }


    @Test
    public void trapMouseCollisionTest() {

        // given
        BoardObject boardObject1 = new Trap(Color.GREEN);
        BoardObject boardObject2 = new Mouse(Color.GRAY);

        // when
        // Collision A -> B
        BoardCell collisionCell1 = processCollision(boardObject1, boardObject2, 10, 0, 0);
        // Collision B -> A
        BoardCell collisionCell2 = processCollision(boardObject1, boardObject2, 10, 0, 0);

        // then
        assertEquals(1, collisionCell1.getBoardObjects().size());
        assertEquals(Color.BLUE, collisionCell1.getBoardObjects().get(0).getColor());

        assertEquals(1, collisionCell2.getBoardObjects().size());
        assertEquals(Color.BLUE, collisionCell2.getBoardObjects().get(0).getColor());
    }

}
