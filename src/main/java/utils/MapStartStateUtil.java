package utils;

import com.google.inject.Inject;
import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import model.board_object_instances.Dalek;
import service.DatabaseService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class MapStartStateUtil {

    private final DatabaseService databaseService;
    Board board;

    @Inject
    public MapStartStateUtil(Board board, DatabaseService databaseService) {
        this.board = board;
        this.databaseService = databaseService;
    }

    public void placeFromDatabase(List<BoardCell> occupiedCells, int roundNumber) {
        LinkedList<Vector2D> daleksPositions = this.databaseService.loadRoundData(roundNumber);

        for (Vector2D daleksPosition : daleksPositions) {
            BoardObject dalek = new Dalek();
            board.addBoardObject(dalek, daleksPosition);
            occupiedCells.add(board.getBoardCell(daleksPosition));
        }
    }


    public void placeRandomly(List<BoardCell> occupiedCells, int numberOfDaleks) {
        List<Vector2D> availableSpots = new ArrayList<>();
        for (int i = 0; i < board.getRows(); i++)
            for (int j = 0; j < board.getRows(); j++)
                if (board.getBoardCell(new Vector2D(i, j)).isEmpty())
                    availableSpots.add(new Vector2D(i, j));

        for (int i = 0; i < numberOfDaleks; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, availableSpots.size());
            Vector2D spawnPlace = availableSpots.get(randomIndex);
            this.board.addBoardObject(new Dalek(), spawnPlace);
            occupiedCells.add(board.getBoardCell(spawnPlace));
            availableSpots.remove(randomIndex);
        }
    }

}
