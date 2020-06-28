package ca.cmpt213.asn2.Model;



import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Stack;

/*
* Grid class has all the logic to make maze board. This class has all the logic
* related to the Maze board. All the GameLogic class gets information
* about the grid from this class.
*/
public class Grid {
    private ArrayList<Cell> grid;
    private final int NUMBER_OF_ROWS = 15;
    private final int NUMBER_OF_COLUMNS = 20;
    private Stack<Cell> neighbourStack;
    private Cell currentCell;


    public Grid() {
        grid = new ArrayList<>();
        neighbourStack = new Stack<>();
        fillGrid();
        createMaze();
        clearCornerWalls();
        removeInnerWalls();
        removeTwoByTwoWalls();

    }



    private void removeInnerWalls() {
        int removeWalls = 20;
        Random rand = new Random();
        int low = 1;
        int maxRow = NUMBER_OF_ROWS - 2;
        int maxCol = NUMBER_OF_COLUMNS - 2;
        while (removeWalls > 0) {
            int row = rand.nextInt(maxRow - low) + low;
            int col = rand.nextInt(maxCol - low) + low;
                Cell cell = getCellObject(row, col);
                if (cell.getWall()) {

                    if (checkConstrainOpenCells(cell.getRowNumber(), cell.getColumnNumber())){

                        removeWalls--;

                        continue;
                    }
                    cell.removeWall();

                    removeWalls--;
                }


        }

    }



    private void clearCornerWalls() {
        Cell topLeftCorner = getCellObject(1, 1);
        Cell topRightCorner = getCellObject(1, NUMBER_OF_COLUMNS - 2);
        Cell bottomLeftCorner = getCellObject(NUMBER_OF_ROWS - 2, 1);
        Cell bottomRightCorner = getCellObject(NUMBER_OF_ROWS - 2, NUMBER_OF_COLUMNS - 2);

        topLeftCorner.removeWall();
        topRightCorner.removeWall();
        bottomLeftCorner.removeWall();
        bottomRightCorner.removeWall();

    }

    private void fillGrid() {
        for (int j = 0; j < NUMBER_OF_ROWS; j++) {
            for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
                grid.add(new Cell(j, i));
            }
        }
        currentCell = getCellObject(1, 1);

    }

    private Cell getRandomNeighbour(Cell cell) {
        ArrayList<Cell> currentCellNeighbours = new ArrayList<>();

        //All the neighbours of the current cell


        if (getTopNeighbour(cell) != null && !Objects.requireNonNull(getTopNeighbour(cell)).getCellVisited()) {
            Cell top = getTopNeighbour(cell);
            currentCellNeighbours.add(top);

        }

        if (getRightNeighbour(cell) != null && !Objects.requireNonNull(getRightNeighbour(cell)).getCellVisited()) {
            Cell right = getRightNeighbour(cell);
            currentCellNeighbours.add(right);

        }

        if (getBottomNeighbour(cell) != null && !Objects.requireNonNull(getBottomNeighbour(cell)).getCellVisited()) {
            Cell bottom = getBottomNeighbour(cell);
            currentCellNeighbours.add(bottom);

        }

        if (getLeftNeighbour(cell) != null && !Objects.requireNonNull(getLeftNeighbour(cell)).getCellVisited()) {
            Cell left = getLeftNeighbour(cell);
            currentCellNeighbours.add(left);

        }

        //Returns a random unvisited neighbour of the cell to the calling method

        if (!currentCellNeighbours.isEmpty()) {
            Random rand = new Random();
            int n = rand.nextInt(currentCellNeighbours.size());
            return currentCellNeighbours.get(n);

        }
        return null;


    }


    private Cell getTopNeighbour(Cell cell) {
        int row = cell.getRowNumber() - 1;
        int col = cell.getColumnNumber();

        if (isCellPositionValid(row, col)) {
            return getCellObject(row, col);
        }


        return null;
    }

    private Cell getRightNeighbour(Cell cell) {
        int row = cell.getRowNumber();
        int col = cell.getColumnNumber() + 1;

        if (isCellPositionValid(row, col)) {
            return getCellObject(row, col);
        }

        return null;
    }

    private Cell getBottomNeighbour(Cell cell) {
        int row = cell.getRowNumber() + 1;
        int col = cell.getColumnNumber();
        if (isCellPositionValid(row, col)) {
            return getCellObject(row, col);
        }

        return null;
    }

    private Cell getLeftNeighbour(Cell cell) {
        int row = cell.getRowNumber();
        int col = cell.getColumnNumber() - 1;

        if (isCellPositionValid(row, col)) {

            return getCellObject(row, col);
        }

        return null;
    }


    // returns a cell object from the grid using given row and column
    public Cell getCellObject(int row, int col) {
        for (Cell c : grid) {
            if (c.getColumnNumber() == col && c.getRowNumber() == row) {
                return c;

            }
        }
        return null;

    }

    /*
     * Maze is generated by Depth First Search using Recursive backtracking.
     * */
    private void createMaze() {

        //1. Choose the initial cell, mark it as visited and push it to the stack
        currentCell.setCellVisited();
        neighbourStack.push(currentCell);

        //2. While the stack is not empty
        while (!neighbourStack.isEmpty()) {
            //2.1 Pop a cell from the stack and make it a current cell
            currentCell = neighbourStack.pop();
            //2.2 If the current cell has any neighbours which have not been visited
            while (getRandomNeighbour(currentCell) != null) {

                //2.2.1 Push the current cell to the stack
                neighbourStack.push(currentCell);
                //2.2.2 Choose one of the unvisited neighbours
                Cell chosenNeighbor = getRandomNeighbour(currentCell);
                //2.2.3 Remove the wall between the current cell and the chosen cell
                currentCell.removeWall();

                //2.2.4 Mark the chosen cell as visited and push it to the stack
                chosenNeighbor.setCellVisited();
                neighbourStack.push(chosenNeighbor);

            }


        }
    }


    public Cell getRandomCellForPower() {

        Random rand = new Random();
        int low = 1;
        int maxRow = NUMBER_OF_ROWS - 2;
        int maxCol = NUMBER_OF_COLUMNS - 2;
        while (true) {
            int row = rand.nextInt(maxRow - low) + low;
            int col = rand.nextInt(maxCol - low) + low;
            Cell cell = getCellObject(row, col);
            if (!(cell.getWall() || cell.isCellIsHero())) {
                return cell;
            }

        }


    }

    public boolean isCellPositionValid(int row, int col) {
        return row > 0 && col > 0 && row < NUMBER_OF_ROWS - 1 && col < NUMBER_OF_COLUMNS - 1;
    }


    private boolean checkConstrainOpenCells(int j , int i){

            //if all cells around are inside the maze board i.e. without borders
            if (isCellPositionValid(j,i) &&
                    isCellPositionValid(j, i+1) &&
                    isCellPositionValid(j+1, i) &&
                    isCellPositionValid(j+1, i+1)) {


                //check if all are open cells
                if (!(getCellObject(j, i + 1).getWall() ||
                        getCellObject(j + 1, i).getWall() ||
                        getCellObject(j + 1, i + 1).getWall()))
                {
                    return true;
                }
            }
            return false;
    }

    private void removeTwoByTwoWalls(){
        for (int j = 0; j < NUMBER_OF_ROWS; j++) {
            for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {

                //if all cells around are inside the maze i.e. without borders
                if (isCellPositionValid(j,i) &&
                        isCellPositionValid(j, i+1) &&
                        isCellPositionValid(j+1, i) &&
                        isCellPositionValid(j+1, i+1))
                {


                    Cell cell = getCellObject(j,i);
                    //check if all are walls
                    if ((cell.getWall() &&
                            getCellObject(j, i + 1).getWall() &&
                            getCellObject(j + 1, i).getWall() &&
                            getCellObject(j + 1, i + 1).getWall()))
                    {
                        cell.removeWall();
                    }

                }

            }
        }

    }

}
