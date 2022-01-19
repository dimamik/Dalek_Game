package model;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

public class Board {
    private final int cols;
    private final int rows;
    private final BoardCell[][] boardCells;

    @Inject
    @Singleton
    public Board(@Named("cols") int cols, @Named("rows") int rows) {
        this.cols = cols;
        this.rows = rows;
        this.boardCells = new BoardCell[cols][rows];

        this.initializeBoard();
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public BoardCell getBoardCell(Vector2D position) {
        return boardCells[position.x()][position.y()];
    }

    public void addBoardObject(BoardObject boardObject, Vector2D position) {
        getBoardCell(position).addBoardObject(boardObject);
    }

    public void clearBoard() {
        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                boardCells[i][j].clearBoardCell();
            }
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                Vector2D position = new Vector2D(i, j);
                BoardCell boardCell = new BoardCell(position);
                boardCells[i][j] = boardCell;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                if (boardCells[i][j].getTopBoardObject().isPresent()) {
                    sb.append(boardCells[i][j].toString());
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
