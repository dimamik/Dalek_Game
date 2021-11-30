package model;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;

public class Board {
    private final int cols;
    private final int rows;
    private List<BoardCell> boardCells;

    @Inject
    public Board(@Named("colsNo") int colsNo, @Named("rowsNo") int rowsNo) {
        this.cols = colsNo;
        this.rows = rowsNo;
        this.boardCells = new LinkedList<>();

        for (int i = 0; i <= colsNo; i++) {
            for (int j = 0; j <= rowsNo; j++) {
                Vector2D position = new Vector2D(i, j);
                BoardCell boardCell = new BoardCell(position);
                boardCells.add(boardCell);
            }
        }
    }

    public int getColsNumber() {
        return cols;
    }

    public int getRowsNumber() {
        return rows;
    }

    public List<BoardCell> getBoardCells() {
        return this.boardCells;
    }

    @Override
    public String toString() {
        return "Board{" +
                "cols=" + cols +
                ", rows=" + rows;
    }
}
