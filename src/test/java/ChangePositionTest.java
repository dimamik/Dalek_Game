import javafx.scene.paint.Color;
import model.Board;
import model.BoardCell;
import model.Vector2D;
import model.board_object_instances.Cat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.CollisionHandler;
import utils.PositionUtil;


public class ChangePositionTest {

    @Test
    public void catMotionTest() {
        // given
        Board board = new Board(10, 10);
        CollisionHandler collisionHandler = new CollisionHandler(board);
        PositionUtil positionUtil = new PositionUtil(board, collisionHandler);
        Cat cat = new Cat(Color.BLACK);

        BoardCell sourceCell = positionUtil.getBoard().getBoardCell(new Vector2D(0, 0));
        BoardCell expectedCell = positionUtil.getBoard().getBoardCell(new Vector2D(2, 1));


        sourceCell.getBoardObjects().add(cat);

        // when
        positionUtil.changePosition(sourceCell, cat.getSingleMove());

        // then
        Assertions.assertEquals(0, sourceCell.getBoardObjects().size());
        Assertions.assertEquals(Color.BLACK, expectedCell.getBoardObjects().get(0).getColor());
    }
}
