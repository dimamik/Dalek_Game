package model;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;

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

    public BoardCell[][] getBoardCells() {
        return boardCells;
    }

    public BoardCell getBoardCell(Vector2D position) {
        return boardCells[position.x()][position.y()];
    }

    public Optional<BoardObject> getBoardObject(Vector2D position) {
        return getBoardCell(position).getTopBoardObject();
    }

    public void addBoardObject(BoardObject boardObject, Vector2D position) {
        getBoardCell(position).addBoardObject(boardObject);
    }

    public void removeBoardObject(BoardObject boardObject, Vector2D position) {
        getBoardCell(position).removeBoardObject(boardObject);
    }

    public void clearBoard() {
        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                BoardCell boardCell = getBoardCell(new Vector2D(i, j));
                boardCell.clearBoardCell();
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
        return "Board{" + "cols=" + cols + ", rows=" + rows + "}";
    }
}
