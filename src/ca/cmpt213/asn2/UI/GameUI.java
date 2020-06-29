package ca.cmpt213.asn2.UI;

import ca.cmpt213.asn2.Model.Cell;
import ca.cmpt213.asn2.Model.Grid;

import java.util.Scanner;

/*
 * GameUI class all the UI logic. Only this class interacts with user input and printing to
 * the console. GameLogic class prints all the messages and gets all the
 * user inputs from this class.
 */
public class GameUI {
    private final int NUMBER_OF_ROWS = 15;
    private final int NUMBER_OF_COLUMNS = 20;
    private final String HERO = "@";
    private final String MONSTER = "!";
    private final String POWER = "$";
    private final String WALL = "#";
    private final String INVISIBLE = ".";
    private final String EMPTY = " ";
    private final String LOSE = "X";

    public void printHelpMenu() {
        System.out.println("DIRECTIONS");
        System.out.println("    Kill 3 Monsters!");
        System.out.println("LEGEND:");
        System.out.println("    #: Wall");
        System.out.println("    @: You (a hero)");
        System.out.println("    !: Monster");
        System.out.println("    $: Power");
        System.out.println("    .: Unexplored space");
        System.out.println("MOVES:");
        System.out.println("    Use W (up), A (left), S (down) and D (right) to move.");
        System.out.println("    (You must press enter after each move).");
    }

    public String getHeroMoveInput() {
        System.out.print("Enter your move [WASD?]: ");
        Scanner userInput = new Scanner(System.in);
        return (userInput.next().toUpperCase());
    }

    public void printStartUp() {
        //PRINTOUT FOR GAME STARTUP
        System.out.println("---------");
        System.out.println("1) Start");
        System.out.println("---------\n");
        printHelpMenu();


    }


    public void printInvalidCommand() {
        System.out.println("Invalid move. Please enter just A (left), S (down), D (right), or W (up) or ? (Help)");
    }

    public void printInvalidMove() {
        System.out.println("Invalid move: you cannot move through walls!");
    }

    public void printWinMessage() {
        System.out.println("Bravo! You won the Game");
    }

    public void printHeroKilledMessage() {
        System.out.println("I'm sorry, you have been eaten!");
    }

    public void printMaze(Grid grid) {
        System.out.println("Maze:");
        for (int j = 0; j < NUMBER_OF_ROWS; j++) {
            for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
                Cell cell = grid.getCellObject(j, i);
                if (cell.getWall()) {
                    System.out.print(WALL);
                } else if (cell.isCellIsHero()) {
                    System.out.print(HERO);
                } else if (cell.isCellIsPower()) {
                    System.out.print(POWER);
                } else if (cell.isCellIsMonster()) {
                    System.out.print(MONSTER);
                } else {
                    System.out.print(INVISIBLE);
                }
            }
            System.out.println();

        }

    }

    public void printHiddenMaze(Grid grid, int winStatus) {
        System.out.println("Maze:");
        for (int j = 0; j < NUMBER_OF_ROWS; j++) {
            for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
                Cell cell = grid.getCellObject(j, i);
                if (cell.getWall() && cell.getCellHideStatus()) {
                    System.out.print(WALL);
                } else if (cell.isCellIsHero()) {
                    if (winStatus == -1) {
                        System.out.print(LOSE);
                    } else {
                        System.out.print(HERO);
                    }

                } else if (cell.isCellIsMonster()) {
                    System.out.print(MONSTER);
                } else if (cell.isCellIsPower()) {
                    System.out.print(POWER);
                } else if (cell.isCellBlank()) {
                    System.out.print(EMPTY);
                } else if (!cell.getWall() && cell.getCellHideStatus()) {
                    System.out.print(EMPTY);
                } else {
                    System.out.print(INVISIBLE);
                }
            }
            System.out.println();

        }

    }

    public void printGameStatAfterEachMove(int numPowerOfHero, int monsterAlive, int monsterToKill) {
        System.out.println("Total number of monsters to be killed: " + monsterToKill);
        System.out.println("Number of powers currently in possession: " + numPowerOfHero);
        System.out.println("Number of monsters alive:" + monsterAlive);
    }

}
