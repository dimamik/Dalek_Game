package utils;

import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

public class GameStateHistoryUtil {
    LinkedList<HashMap<Vector2D, LinkedList<BoardObject>>> history;

    public GameStateHistoryUtil() {
        this.history = new LinkedList<>();
    }

    public void recordDay(Board board) {

        HashMap<Vector2D, LinkedList<BoardObject>> currentDay = new HashMap<>();

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                BoardCell boardCell = board.getBoardCell(new Vector2D(i, j));
                if (!boardCell.isEmpty()) {
                    currentDay.put(new Vector2D(i, j), new LinkedList<>(boardCell.getBoardObjects()));
                }
            }
        }

        this.history.addLast(currentDay);
    }

    public Optional<HashMap<Vector2D, LinkedList<BoardObject>>> popLastDay() {
        if (history.size() > 0) {
            return Optional.of(history.removeLast());
        } else {
            return Optional.empty();
        }

    }

    public void reset() {
        history.clear();
    }
}
