import javafx.scene.paint.Color;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import model.board_object_instances.Cat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.CollisionHandler;

public class BoardViewTest {

    @Test
    public void testBoardView() {

        new Main();

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
}
