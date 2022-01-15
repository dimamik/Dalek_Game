package utils;

import enums.ObjectType;
import model.Board;
import model.BoardCell;
import model.Vector2D;

import java.util.HashMap;
import java.util.LinkedList;


public class GameStateHistoryUtil {


    LinkedList<HashMap<Vector2D, ObjectType>> history;

    public GameStateHistoryUtil() {
        this.history = new LinkedList<>();
    }

    public void recordDay(Board board) {

        HashMap<Vector2D, ObjectType> currentDay = new HashMap<>();

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                BoardCell boardCell = board.getBoardCell(new Vector2D(i, j));
                if (!boardCell.isEmpty()) {
                    if (boardCell.getBoardObjects().size() > 1) {
                        throw new IllegalStateException("BoardCell should have only one BoardObject");
                    }
                    currentDay.put(new Vector2D(i, j), boardCell.getBoardObjects().get(0).getType());
                }
            }
        }

        this.history.addLast(currentDay);
    }

    public HashMap<Vector2D, ObjectType> popLastDay() {
        return this.history.removeLast();
    }


}
