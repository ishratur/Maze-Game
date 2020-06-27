package ca.cmpt213.asn2.Model;

public class Cell {
    private int rowNumber;
    private int columnNumber;
    private boolean wall;
    private boolean isCellVisited;

    private boolean isCellIsHero;
    private int numMonsters;
    private boolean isCellIsPower;
    private boolean hideCell;
    private boolean makeCellBlank;


    public Cell(int rowNumber, int columnNumber) {
        this.columnNumber = columnNumber;
        this.rowNumber = rowNumber;
        this.isCellVisited = false;
        this.wall = true;

        this.isCellIsHero = false;
        this.numMonsters = 0;
        this.isCellIsPower = false;
//        this.hideCell = true;



    }
    public void setCellBlank(){
        this.makeCellBlank = true;
    }

    public boolean isCellBlank(){
        return makeCellBlank;
    }

    public boolean getCellHideStatus() {
        return hideCell;
    }

    public void removeHideCell(boolean status) {
        this.hideCell = status;
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

    public void setWall() {
        this.wall = true;
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
                +rowNumber +
                "," + columnNumber +
                '}' + " visited: " + isCellVisited +
                "; wall: " + getWall();
    }

    public boolean isCellIsHero() {
        return isCellIsHero;
    }

    public void setCellIsHero(boolean isHero) {
        isCellIsHero = isHero;
    }

    public boolean isCellIsMonster() {
        return numMonsters > 0;
    }

    public void incrementMonster() {
        numMonsters++;
    }

    public void decrementMonster() {
        numMonsters--;
    }

    public boolean isCellIsPower() {
        return isCellIsPower;
    }

    public void setCellIsPower(boolean isPower) {
        isCellIsPower = isPower;
    }


}
