import enums.Direction;
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

// TODO OUTDATED
public class GameResultTest {

    @Test
    public void gameEndTest() {
//        FIXME -> Write some sensible tests pls
        // given
        Board board = new Board(10, 10);
        PositionUtil positionUtil = new PositionUtil(board, new CollisionHandler(board, mock(CollisionActionFactory.class)));
        BoardCell doctorSourceCell = positionUtil.getBoard().getBoardCell(new Vector2D(0, 0));
        BoardCell dalekSourceCell = positionUtil.getBoard().getBoardCell(new Vector2D(1, 0));
        BoardObject dalek = new Dalek();
        BoardObject doctor = new Doctor();
        List<BoardCell> occupiedCells = new ArrayList<>();

        // when
        doctorSourceCell.getBoardObjects().add(doctor);
        dalekSourceCell.getBoardObjects().add(dalek);
        occupiedCells.add(doctorSourceCell);
        occupiedCells.add(dalekSourceCell);
        positionUtil.move(doctorSourceCell, Direction.E.toUnitVector());

        // then
        Assertions.assertTrue(positionUtil.isGameEnded(occupiedCells, dalekSourceCell));
    }
}
