package ca.cmpt213.asn2.Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Grid {
    // Making the grid
    private ArrayList<Cell> grid;
    private final int NUMBER_OF_ROWS = 15;
    private final int NUMBER_OF_COLUMNS = 20;
    private Stack<Cell> neighbourStack;
    private Cell currentCell;

    private final String HERO = "@";
    private final String MONSTER = "!";
    private  final String POWER = "$";
    private final String WALL = "#";
    private final String INVISIBLE = ".";





    public Grid() {
        grid =  new ArrayList<>();
        neighbourStack = new Stack<>();
        fillGrid();
        createMaze();
        printGrid();



    }
     public void fillGrid(){
         for (int j = 0; j < NUMBER_OF_ROWS; j++) {
             for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
                 grid.add(new Cell(j,i));
             }
         }
//         currentCell = grid.get(0);
         currentCell = getCellObject(1,1);
     }

     public Cell getRandomNeighbour(Cell cell){
         ArrayList<Cell> currentCellNeighbours = new ArrayList<>();

        //All the neighbours of the current cell
         System.out.print("***Current Cell: ");
         System.out.println(cell.toString());
         System.out.println("-----------------------------");



        if (getTopNeighbour(cell) != null && getTopNeighbour(cell).getCellVisited() == false){
            Cell top = getTopNeighbour(cell);
            currentCellNeighbours.add(top);
            System.out.println("top: " + top.toString());
        }

        if (getRightNeighbour(cell) != null && getRightNeighbour(cell).getCellVisited() == false){
            Cell right = getRightNeighbour(cell);
            currentCellNeighbours.add(right);
           System.out.println("right: " + right.toString());
        }

        if (getBottomNeighbour(cell) != null && getBottomNeighbour(cell).getCellVisited() == false){
            Cell bottom = getBottomNeighbour(cell);
            currentCellNeighbours.add(bottom);
            System.out.println("bottom: " + bottom.toString());
        }

        if (getLeftNeighbour(cell) != null && getLeftNeighbour(cell).getCellVisited() == false ){
            Cell left = getLeftNeighbour(cell);
            currentCellNeighbours.add(left);
            System.out.println("left: " + left.toString());
        }

         //print neighbour array element

/*         System.out.println("Neighbor Array: ");
         currentCellNeighbours.forEach(System.out::println);
         System.out.println("-----------------------------------");*/


        //Returns a random neighbour of the cell to the calling method

         if (!currentCellNeighbours.isEmpty()){
             Random rand = new Random();
             int n = rand.nextInt(currentCellNeighbours.size());
             return currentCellNeighbours.get(n);

         }
         return null;


     }

   /*  public Cell getTopNeighbour(Cell cell){
         int row = cell.getRowNumber() - 1;
         int col = cell.getColumnNumber();

         if (row >= 0 && col >= 0 && row <= NUMBER_OF_ROWS - 1 && col <= NUMBER_OF_COLUMNS - 1){
             for (Cell c: grid){
                 if (c.getRowNumber() == row && c.getColumnNumber() == col){
                     return c;
                 }
             }
         }


         return null;
     }

    public Cell getRightNeighbour(Cell cell){
        int row = cell.getRowNumber();
        int col = cell.getColumnNumber() + 1;

        if (row >= 0 && col >= 0 && row <= NUMBER_OF_ROWS - 1 && col <= NUMBER_OF_COLUMNS - 1){
            for (Cell c: grid){
                if (c.getRowNumber() == row && c.getColumnNumber() == col){
                    return c;
                }
            }
        }

        return null;
    }

    public Cell getBottomNeighbour(Cell cell){
        int row = cell.getRowNumber() + 1;
        int col = cell.getColumnNumber();
        if (row >= 0 && col >= 0 && row <= NUMBER_OF_ROWS - 1 && col <= NUMBER_OF_COLUMNS - 1){
            for (Cell c: grid){
                if (c.getRowNumber() == row && c.getColumnNumber() == col){
                    return c;
                }
            }
        }

        return null;
    }

    public Cell getLeftNeighbour(Cell cell){
        int row = cell.getRowNumber();
        int col = cell.getColumnNumber() - 1;

        if (row >= 0 && col >= 0 && row <= NUMBER_OF_ROWS - 1 && col <= NUMBER_OF_COLUMNS - 1){
            for (Cell c: grid){
                if (c.getRowNumber() == row && c.getColumnNumber() == col){
                    return c;
                }
            }
        }

        return null;
    }

*/


     public void printGrid(){
         //Print Grid
         System.out.println("PRINTING THE MAZE \n");
         for (int j = 0; j < NUMBER_OF_ROWS; j++) {
             for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
                 Cell temp = getCellObject(j,i);
                 if (temp.getWall()){
                     System.out.print(WALL);
                 }
                 else {
                     System.out.print(INVISIBLE);
                 }

             }
             System.out.println();
         }

     }


//    --------------------------------------New Neighbor----------------------------------------------------------------------

    public Cell getTopNeighbour(Cell cell){
        int row = cell.getRowNumber() - 1;
        int col = cell.getColumnNumber();

        if (row > 0 && col > 0 && row < NUMBER_OF_ROWS - 1 && col < NUMBER_OF_COLUMNS - 1){
            for (Cell c: grid){
                if (c.getRowNumber() == row && c.getColumnNumber() == col){
                    return c;
                }
            }
        }


        return null;
    }

    public Cell getRightNeighbour(Cell cell){
        int row = cell.getRowNumber();
        int col = cell.getColumnNumber() + 1;

        if (row > 0 && col > 0 && row < NUMBER_OF_ROWS - 1 && col < NUMBER_OF_COLUMNS - 1){
            for (Cell c: grid){
                if (c.getRowNumber() == row && c.getColumnNumber() == col){
                    return c;
                }
            }
        }

        return null;
    }

    public Cell getBottomNeighbour(Cell cell){
        int row = cell.getRowNumber() + 1;
        int col = cell.getColumnNumber();
        if (row > 0 && col > 0 && row < NUMBER_OF_ROWS - 1 && col < NUMBER_OF_COLUMNS - 1){
            for (Cell c: grid){
                if (c.getRowNumber() == row && c.getColumnNumber() == col){
                    return c;
                }
            }
        }

        return null;
    }

    public Cell getLeftNeighbour(Cell cell){
        int row = cell.getRowNumber();
        int col = cell.getColumnNumber() - 1;

        if (row > 0 && col > 0 && row < NUMBER_OF_ROWS - 1 && col < NUMBER_OF_COLUMNS - 1){
            for (Cell c: grid){
                if (c.getRowNumber() == row && c.getColumnNumber() == col){
                    return c;
                }
            }
        }

        return null;
    }


//    ------------------------------------------------------------------------------------------------------------------



     // returns a cell object from the grid using given row and column
     public Cell getCellObject(int row, int col){
         for (Cell c: grid){
             if (c.getColumnNumber() == col && c.getRowNumber() == row){
                 return c;

             }
         }
         return null;

     }
    /*
     * Maze is generated by Depth First Search using Recursive backtracking.
     * */
     public void createMaze(){
         //1. Choose the initial cell, mark it as visited and push it to the stack
         currentCell.setCellVisited();
         neighbourStack.push(currentCell);

         //2. While the stack is not empty
         while (!neighbourStack.isEmpty()){
             //2.1 Pop a cell from the stack and make it a current cell
             currentCell = neighbourStack.pop();
             //2.2 If the current cell has any neighbours which have not been visited
             if (getRandomNeighbour(currentCell) != null){
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
             System.out.println("++++Stack: ");
             neighbourStack.forEach(System.out::println);

         }
     }


}
