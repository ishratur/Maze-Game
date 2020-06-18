package ca.cmpt213.asn2.Model;

import java.util.ArrayList;

public class Grid {
    // Making the grid
    private ArrayList<Cell> grid;


    public Grid() {
        grid =  new ArrayList<>();
        fillGrid();
    }
     public void fillGrid(){
         int rows = 13;
         int columns = 18;
         for (int j = 0; j < rows; j++) {
             for (int i = 0; i < columns; i++) {
                 grid.add(new Cell(j,i));
             }
         }
     }

     public void checkNeighbours(Cell cell){

        Cell top = getTopNeighbour(cell);
        System.out.println("top: " + top.toString());

        Cell right = getRightNeighbour(cell);
        System.out.println("right: " + right.toString());

         Cell bottom = getBottomNeighbour(cell);
         System.out.println("bottom: " + bottom.toString());

         Cell left = getLeftNeighbour(cell);
         System.out.println("left: " + left.toString());



//         Cell topNeighbour = grid.g
         //Cell rightNeighbour;
         //Cell bottomNeighbour;
         //Cell leftNeighbour;

     }

     public Cell getTopNeighbour(Cell cell){
         int row = cell.getRowNumber() - 1;
         int col = cell.getColumnNumber();

         for (Cell c: grid){
             if (c.getRowNumber() == row && c.getColumnNumber() == col){
                 return c;
             }
         }
         return null;
     }

    public Cell getRightNeighbour(Cell cell){
        int row = cell.getRowNumber();
        int col = cell.getColumnNumber() + 1;

        for (Cell c: grid){
            if (c.getRowNumber() == row && c.getColumnNumber() == col){
                return c;
            }
        }
        return null;
    }

    public Cell getBottomNeighbour(Cell cell){
        int row = cell.getRowNumber() + 1;
        int col = cell.getColumnNumber();

        for (Cell c: grid){
            if (c.getRowNumber() == row && c.getColumnNumber() == col){
                return c;
            }
        }
        return null;
    }

    public Cell getLeftNeighbour(Cell cell){
        int row = cell.getRowNumber();
        int col = cell.getColumnNumber() - 1;

        for (Cell c: grid){
            if (c.getRowNumber() == row && c.getColumnNumber() == col){
                return c;
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
             if(c.getRowNumber() == 3 && c.getColumnNumber() == 3) {

                 //System.out.print("#");
                 checkNeighbours(c);

             }

         }
     }


}
