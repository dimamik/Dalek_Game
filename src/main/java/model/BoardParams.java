package model;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class BoardParams {

    private final int cols;
    private final int rows;
    private final int cellSize;

    @Inject
    public BoardParams(@Named("cols") int cols, @Named("rows") int rows, @Named("cellSize") int cellSize) {
        this.cols = cols;
        this.rows = rows;
        this.cellSize = cellSize;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCellSize() {
        return cellSize;
    }
}
