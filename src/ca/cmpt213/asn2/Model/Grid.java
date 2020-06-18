package ca.cmpt213.asn2.Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Grid {
    // Making the grid
    private ArrayList<Cell> grid;
    private final int NUMBER_OF_ROWS = 13;
    private final int NUMBER_OF_COLUMNS = 18;
    private Stack<Cell> neighbours;
    private Cell currentCell;


    public Grid() {
        grid =  new ArrayList<>();
        neighbours = new Stack<>();
        fillGrid();
        currentCell = grid.get(50);
        runGrid();
//        printGrid();


    }
     public void fillGrid(){
         for (int j = 0; j < NUMBER_OF_ROWS; j++) {
             for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
                 grid.add(new Cell(j,i));
             }
         }
     }

     public void checkNeighbours(Cell cell){
/*
        if (cell != null && cell.getCellVisited() == false){
            neighbours.push(cell);
        }*/

        if (getTopNeighbour(cell) != null && getTopNeighbour(cell).getCellVisited() == false){
            Cell top = getTopNeighbour(cell);
            neighbours.push(top);
            System.out.println("top: " + top.toString());
        }

        if (getRightNeighbour(cell) != null && getRightNeighbour(cell).getCellVisited() == false){
            Cell right = getRightNeighbour(cell);
            neighbours.push(right);
            System.out.println("right: " + right.toString());
        }

        if (getBottomNeighbour(cell) != null && getBottomNeighbour(cell).getCellVisited() == false){

            Cell bottom = getBottomNeighbour(cell);
            neighbours.push(bottom);
            System.out.println("bottom: " + bottom.toString());
        }

        if (getLeftNeighbour(cell) != null && getLeftNeighbour(cell).getCellVisited() == false ){
            Cell left = getLeftNeighbour(cell);
            neighbours.push(left);
            System.out.println("left: " + left.toString());
        }


//        print stack element
         System.out.println("=======STACK==========");
         neighbours.forEach(System.out::println);
         System.out.println("=================");


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

    public Cell pickRandomNeighbour(){
        if (!neighbours.isEmpty()){
            Random rand = new Random();
            int n = rand.nextInt(neighbours.size());
            return neighbours.get(n);

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

     public void runGrid(){
         currentCell.setCellVisited();
         checkNeighbours(currentCell);
         Cell nextCell = pickRandomNeighbour();

         nextCell.setCellVisited();
         currentCell = nextCell;
         System.out.println(currentCell.toString());



     }


}
