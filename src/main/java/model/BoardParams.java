package model;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class BoardParams {

    private final int colsNo;
    private final int rowsNo;
    private final int cellSize;

    @Inject
    public BoardParams(@Named("colsNo") int colsNo, @Named("rowsNo") int rowsNo, @Named("cellSize") int cellSize) {
        this.colsNo = colsNo;
        this.rowsNo = rowsNo;
        this.cellSize = cellSize;
    }

    public int getColsNo() {
        return colsNo;
    }

    public int getRowsNo() {
        return rowsNo;
    }

    public int getCellSize() {
        return cellSize;
    }
}
