package ca.cmpt213.asn2.Model;

/*
 * Power class has all the data related to Power. This class
 * is used to find all the power related data.
 */
public class Power {
    private int rowNumber;
    private int columnNumber;
    private int powerCount;


    public Power(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.powerCount = 1;
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

    public int getPowerCount() {
        return powerCount;
    }

    public void decrementPowerCount() {
        this.powerCount = 0;
    }

}
