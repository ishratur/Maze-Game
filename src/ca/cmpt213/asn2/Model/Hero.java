package ca.cmpt213.asn2.Model;


/*
 * Hero class has all the information about the Hero.
 * This class tracks all the Hero movements, number of power
 * hero possesses and it's survival status.
 * Collaborators:
 * Ishratur Rahman - 301278317
 * Shawn McGirr - 301358896
 * */
public class Hero {
    private int rowNumber;
    private int columnNumber;
    private int power;
    private boolean isHeroAlive;

    public Hero(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.isHeroAlive = true;
        this.power = 0;
    }

    public int getPower() {
        return this.power;
    }

    public void incrementPower() {
        this.power += 1;
    }

    public void decrementPower() {
        this.power -= 1;
    }

    public int getRowNumber() {
        return rowNumber;
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

    public boolean isHeroAlive() {
        return isHeroAlive;
    }

    public void setHeroDead() {
        isHeroAlive = false;
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
