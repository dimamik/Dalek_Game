package utils;

import model.Board;
import model.BoardCell;
import model.BoardObject;
import model.Vector2D;

import java.util.HashMap;
import java.util.LinkedList;


public class GameStateHistoryUtil {


    //    TODO FIXME THERE WE CAN STORE OBJECT TYPES, WHICH WOULD BE MORE SUFFICIENT
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

    public HashMap<Vector2D, LinkedList<BoardObject>> popLastDay() {
        if (history.size() > 0) {
            return this.history.removeLast();
        } else {
//            TODO Fix this to never happen
            throw new IllegalStateException("No more days to pop");
        }

    }

    public void reset() {
        history.clear();
    }


}
