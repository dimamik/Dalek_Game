package model;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public record BoardParams (int cols, int rows, int cellSize) {
    @Inject
    public BoardParams(@Named("cols") int cols, @Named("rows") int rows, @Named("cellSize") int cellSize) {
        this.cols = cols;
        this.rows = rows;
        this.cellSize = cellSize;
    }
}
