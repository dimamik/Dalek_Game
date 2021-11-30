package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Board {
    private final int width;
    private final int height;
    private List<BoardCell> boardCells;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.boardCells = new LinkedList<>();

        for (int i = 0; i <= width; i++) {
            for (int j = 0; j <= height; j++) {
                Vector2D position = new Vector2D(i, j);
                BoardCell boardCell = new BoardCell(position);
                boardCells.add(boardCell);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<BoardCell> getBoardCells() {
        return this.boardCells;
    }
}
