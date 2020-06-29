package ca.cmpt213.asn2.Model;


/*
 * Monster class has all the information about a Monster.
 * This class tracks all the Monster movements and it's survival status.
 * Collaborators:
 * Ishratur Rahman - 301278317
 * Shawn McGirr - 301358896
 * */
public class Monster {
    private int rowNumber;
    private int columnNumber;
    private boolean alive;


    public Monster(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.alive = true;

    }


    public int getRowNumber() {
        return rowNumber;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void killMonster() {
        this.alive = false;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public void goLeft() {
        columnNumber--;
    }

    public void goRight() {
        columnNumber++;
    }

    public void goDown() {
        rowNumber++;
    }

    public void goUp() {
        rowNumber--;
    }

}
