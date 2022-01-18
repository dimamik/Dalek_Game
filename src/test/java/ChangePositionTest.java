import enums.Direction;
import enums.ObjectType;
import javafx.scene.paint.Color;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import model.board_object_instances.Dalek;
import model.board_object_instances.Doctor;
import model.factories.CollisionActionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.CollisionHandler;
import utils.PositionUtil;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class ChangePositionTest {

    @Test
    public void doctorMotionTest() {
        // given
        Board board = new Board(10, 10);
        PositionUtil positionUtil = new PositionUtil(board, new CollisionHandler(board, mock(CollisionActionFactory.class)));
        BoardCell sourceCell = positionUtil.getBoard().getBoardCell(new Vector2D(0, 0));
        BoardCell expectedCell = positionUtil.getBoard().getBoardCell(new Vector2D(0, 1));
        Direction direction = Direction.S;
        BoardObject doctor = new Doctor();

        // when
        sourceCell.getBoardObjects().add(doctor);
        positionUtil.move(sourceCell, direction.toUnitVector());

        // then
        Assertions.assertEquals(0, sourceCell.getBoardObjects().size());
        Assertions.assertEquals(Color.BLACK, expectedCell.getBoardObjects().get(0).getColor());
    }

    @Test
    public void edgeMotionTest() {
        // given
        Board board = new Board(10, 10);
        PositionUtil positionUtil = new PositionUtil(board, new CollisionHandler(board, mock(CollisionActionFactory.class)));
        BoardCell sourceCell = positionUtil.getBoard().getBoardCell(new Vector2D(9, 0));
        BoardCell expectedCell = positionUtil.getBoard().getBoardCell(new Vector2D(9, 0));
        Direction forbiddenDirection = Direction.NE;
        BoardObject doctor = new Doctor();

        // when
        sourceCell.getBoardObjects().add(doctor);
        positionUtil.move(sourceCell, forbiddenDirection.toUnitVector());

        // then
        Assertions.assertEquals(1, sourceCell.getBoardObjects().size());
        Assertions.assertEquals(expectedCell.getPosition(), sourceCell.getPosition());
    }

    @Test
    public void dalekMotionTest() {
        // given
        Board board = new Board(10, 10);
        PositionUtil positionUtil = new PositionUtil(board, new CollisionHandler(board, mock(CollisionActionFactory.class)));
        BoardObject doctor = new Doctor();
        BoardObject dalek1 = new Dalek();
        BoardObject dalek2 = new Dalek();
        BoardCell doctorCell = positionUtil.getBoard().getBoardCell(new Vector2D(0, 0));
        BoardCell sourceDalek1Cell = positionUtil.getBoard().getBoardCell(new Vector2D(5, 0));
        BoardCell sourceDalek2Cell = positionUtil.getBoard().getBoardCell(new Vector2D(5, 5));
        List<BoardCell> occupiedCells = new ArrayList<>();
        occupiedCells.add(sourceDalek1Cell);
        occupiedCells.add(sourceDalek2Cell);
        BoardCell expectedDalek1Cell = positionUtil.getBoard().getBoardCell(new Vector2D(4, 0));
        BoardCell expectedDalek2Cell = positionUtil.getBoard().getBoardCell(new Vector2D(4, 4));

        // when
        doctorCell.getBoardObjects().add(doctor);
        sourceDalek1Cell.getBoardObjects().add(dalek1);
        sourceDalek2Cell.getBoardObjects().add(dalek2);
        positionUtil.moveAllDaleks(doctorCell.getPosition(), occupiedCells);

        // then
        Assertions.assertEquals(0, sourceDalek1Cell.getBoardObjects().size());
        Assertions.assertEquals(0, sourceDalek2Cell.getBoardObjects().size());
        Assertions.assertEquals(ObjectType.DALEK, expectedDalek1Cell.getBoardObjects().get(0).getType());
        Assertions.assertEquals(ObjectType.DALEK, expectedDalek2Cell.getBoardObjects().get(0).getType());
    }
}
