package ca.cmpt213.asn2.Model;

public class Cell {
    private int rowNumber;
    private int columnNumber;

    private boolean wall;

    //cell visited or not
    private boolean isCellVisited;


    public Cell( int rowNumber, int columnNumber) {
        this.columnNumber = columnNumber;
        this.rowNumber = rowNumber;
        isCellVisited = false;
        wall = true;

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

    public boolean getWall() {
        return wall;
    }

    public void removeWall() {

        this.wall = false;
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
                '}' + " visited: " + isCellVisited +
                "; wall: " + getWall();
    }
}
