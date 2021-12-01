import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import model.board_object_instances.Cat;
import org.junit.jupiter.api.Test;
import utils.CollisionHandler;
import utils.PositionUtil;

import java.awt.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ChangePositionTest {

    @Test
    public void catMotionTest() {
        // given
        Board board = new Board(10, 10);
        PositionUtil positionUtil = new PositionUtil(board);
        Cat cat = new Cat(Color.BLACK);

        BoardCell sourceCell = positionUtil.getBoard().getBoardCells().get(0);
        BoardCell expectedCell = positionUtil.getBoard().getBoardCells()
                .stream()
                .filter(cell -> cell.getPosition().equals(new Vector2D(2, 1)))
                .collect(Collectors.toList())
                .get(0);

        sourceCell.getBoardObjects().add(cat);

        // when
        positionUtil.changePosition(sourceCell, cat.getSingleMove());

        // then
        assertEquals(0, sourceCell.getBoardObjects().size());
        assertEquals(Color.BLACK, expectedCell.getBoardObjects().get(0).getColor());
    }


}
