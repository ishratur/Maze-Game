package ca.cmpt213.asn2.Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Grid {
    // Making the grid
    private ArrayList<Cell> grid;
    private final int NUMBER_OF_ROWS = 13;
    private final int NUMBER_OF_COLUMNS = 18;
    private Stack<Cell> neighbourStack;
    private Cell currentCell;


/*    private final int NUMBER_OF_ROWS = 3;
    private final int NUMBER_OF_COLUMNS = 4;*/



    public Grid() {
        grid =  new ArrayList<>();
        neighbourStack = new Stack<>();
        fillGrid();
        createMaze();
//        printGrid();


    }
     public void fillGrid(){
         for (int j = 0; j < NUMBER_OF_ROWS; j++) {
             for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
                 grid.add(new Cell(j,i));
             }
         }
         currentCell = grid.get(0);
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

         System.out.println("Neighbor Array: ");
         currentCellNeighbours.forEach(System.out::println);
         System.out.println("-----------------------------------");


        //Returns a random neighbour of the cell to the calling method

         if (!currentCellNeighbours.isEmpty()){
             Random rand = new Random();
             int n = rand.nextInt(currentCellNeighbours.size());
             return currentCellNeighbours.get(n);

         }
         return null;


     }

     public Cell getTopNeighbour(Cell cell){
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



     public void printGrid(){
         //Print Grid
         System.out.println("");
         for (Cell c: grid){
             if (c.getColumnNumber() == 17){
                 System.out.println();
             }
             if(c.getCellVisited()) {

                 System.out.print(".");
//                 checkNeighbours(c);
//                 System.out.println(pickRandomNeighbour().toString());

             }
             if (!c.getCellVisited()){
                 System.out.print("#");
             }

         }
     }

     public void createMaze(){
        //set the current cell as visited
         currentCell.setCellVisited();
         //set the current cell to the stack
         neighbourStack.push(currentCell);
         //cell to hold the next cell to be visited
         Cell nextCell = null;

         //until stack is not empty..
         while (!neighbourStack.isEmpty()){
             Cell randomCell = getRandomNeighbour(currentCell);

             //until there is valid moves

             if (randomCell != null){
                 //this will be the next cell to visit. Find this cell in the maze
                 for (Cell c: grid){
                     if (c.getColumnNumber() == randomCell.getColumnNumber() && c.getRowNumber() == randomCell.getRowNumber()){
                         nextCell = c;
                         break;
                     }
                 }
                 neighbourStack.push(nextCell);
                 nextCell.setCellVisited();
                 currentCell = nextCell;

                 System.out.println("Stack: ");
                 neighbourStack.forEach(System.out::println);
                 System.out.println("-----------------");


             }
             // backtrack when no more valid moves left for the current cell
             else {
                 System.out.println("=======Backtracking=========");
                 System.out.println("Stack: ");
                 neighbourStack.forEach(System.out::println);
                 nextCell = neighbourStack.pop();

                 System.out.println("-----------------");
                 for (Cell c: grid){
                     if (c.getColumnNumber() == nextCell.getColumnNumber() && c.getRowNumber() == nextCell.getRowNumber()){
                         currentCell = c;
                         break;
                     }
                 }
             }
         }




     }


}
