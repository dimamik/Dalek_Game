import javafx.scene.paint.Color;
import model.Board;
import model.BoardCell;
import model.MovableBoardObject;
import model.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.CollisionHandler;
import utils.PositionUtil;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;


public class ChangePositionTest {

    public List<BoardCell> processMotion(MovableBoardObject boardObject, int boardSize, int x0, int y0, int x1, int y1) {
        //given
        Board board = new Board(boardSize, boardSize);
        PositionUtil positionUtil = new PositionUtil(board, mock(CollisionHandler.class));
        BoardCell sourceCell = positionUtil.getBoard().getBoardCell(new Vector2D(x0, y0));
        BoardCell expectedCell = positionUtil.getBoard().getBoardCell(new Vector2D(x1, y1));

        // when
        sourceCell.getBoardObjects().add(boardObject);
        positionUtil.changePosition(sourceCell, boardObject.getSingleMove());

        List<BoardCell> cells = new ArrayList<>();
        cells.add(sourceCell);
        cells.add(expectedCell);

        return cells;
    }

    @Test
    public void motionTest() {
        // given
        int sourceCellIndex = 0;
        int expectedCellIndex = 1;
        MovableBoardObject cat = new Cat(Color.BLACK);

        // when
        List<BoardCell> cells = processMotion(cat, 10, 0, 0, 2, 1);

        // then
        Assertions.assertEquals(0, cells.get(sourceCellIndex).getBoardObjects().size());
        Assertions.assertEquals(Color.BLACK, cells.get(expectedCellIndex).getBoardObjects().get(0).getColor());
    }
}
