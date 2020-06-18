package ca.cmpt213.asn2.Model;

import java.util.ArrayList;

public class Grid {
    // Making the grid
    private ArrayList<Cell> grid;
    private final int NUMBER_OF_ROWS = 13;
    private final int NUMBER_OF_COLUMNS = 18;


    public Grid() {
        grid =  new ArrayList<>();
        fillGrid();
    }
     public void fillGrid(){
         for (int j = 0; j < NUMBER_OF_ROWS; j++) {
             for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
                 grid.add(new Cell(j,i));
             }
         }
     }

     public void checkNeighbours(Cell cell){

        if (getTopNeighbour(cell) != null){
            Cell top = getTopNeighbour(cell);
            System.out.println("top: " + top.toString());
        }

        if (getRightNeighbour(cell) != null){
            Cell right = getRightNeighbour(cell);
            System.out.println("right: " + right.toString());
        }

        if (getBottomNeighbour(cell) != null){

            Cell bottom = getBottomNeighbour(cell);
            System.out.println("bottom: " + bottom.toString());
        }

        if (getLeftNeighbour(cell) != null ){
            Cell left = getLeftNeighbour(cell);
            System.out.println("left: " + left.toString());
        }

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
         for (Cell c: grid){
             if (c.getColumnNumber() == 17){
                 System.out.println();
             }
             if(c.getRowNumber() == 0 && c.getColumnNumber() == 17) {

                 //System.out.print("#");
                 checkNeighbours(c);

             }

         }
     }


}
