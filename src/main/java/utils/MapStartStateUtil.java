package utils;

import model.Board;
import model.BoardCell;
import model.Vector2D;
import model.board_object_instances.Dalek;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class MapStartStateUtil {

    Board board;
    String PATH_TO_ROUNDS = "rounds";

    //    TODO this Util is responsible for initializing map with daleks randomly or load them from file
    public MapStartStateUtil(Board board) {
        this.board = board;
    }

    public void placeFromDatabase(List<BoardCell> occupiedCells, int roundNumber) {
//        TODO load daleks from database
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
