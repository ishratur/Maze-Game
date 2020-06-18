package ca.cmpt213.asn2.Model;

public class Cell {
    private int rowNumber;
    private int columnNumber;

    // walls -> top, right, bottom, left
    private boolean walls [] = {true, true, true,true};

    //cell visited or not
    private boolean isCellVisited;


    public Cell( int rowNumber, int columnNumber) {
        this.columnNumber = columnNumber;
        this.rowNumber = rowNumber;
        isCellVisited = false;

    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }
    public boolean[] getWalls() {
        return walls;
    }

    public void setWalls(boolean[] walls) {
        this.walls = walls;
    }

    public boolean getCellVisited() {
        return isCellVisited;
    }

    public void setCellVisited() {
        isCellVisited = true;
    }


    @Override
    public String toString() {
        return "{" +
                + rowNumber +
                "," + columnNumber +
                '}' + " visited: " + isCellVisited ;
    }
}
