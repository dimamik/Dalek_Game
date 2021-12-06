package model;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

public class Board {
    private final int cols;
    private final int rows;
    private final BoardCell[][] boardCells;

    @Inject
    public Board(@Named("cols") int cols, @Named("rows") int rows) {
        this.cols = cols;
        this.rows = rows;
        this.boardCells = new BoardCell[cols][rows];

        this.initializeBoard();
    }

    public BoardCell[][] getBoardCells() {
        return boardCells;
    }

    public BoardCell getBoardCell(Vector2D position) {
        return boardCells[position.x()][position.y()];
    }

    public Optional<BoardObject> getBoardObject(Vector2D position) {
        return getBoardCell(position).getBoardObject();
    }

    public void addBoardObject(BoardObject boardObject, Vector2D position) {
        getBoardCell(position).addBoardObject(boardObject);
    }

    public void removeBoardObject(BoardObject boardObject, Vector2D position) {
        getBoardCell(position).removeBoardObject(boardObject);
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
