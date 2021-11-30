package model;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class BoardParams {

    private int colsNo;
    private int rowsNo;
    private int cellSize;

    @Inject
    public BoardParams(@Named("colsNo") int colsNo, @Named("rowsNo") int rowsNo, @Named("cellSize") int cellSize) {
        this.colsNo = colsNo;
        this.rowsNo = rowsNo;
        this.cellSize = cellSize;
    }

    public int getColsNo() {
        return colsNo;
    }

    public void setColsNo(int colsNo) {
        this.colsNo = colsNo;
    }

    public int getRowsNo() {
        return rowsNo;
    }

    public void setRowsNo(int rowsNo) {
        this.rowsNo = rowsNo;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

}
