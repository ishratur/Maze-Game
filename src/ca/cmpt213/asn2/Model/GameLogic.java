package ca.cmpt213.asn2.Model;


import ca.cmpt213.asn2.UI.GameUI;

import java.util.*;

public class GameLogic {
    private final String UP_COMMAND = "W";
    private final String DOWN_COMMAND = "S";
    private final String LEFT_COMMAND = "A";
    private final String RIGHT_COMMAND = "D";
    private final String HELP_COMMAND = "?";
    private final String REVEAL_MAZE_COMMAND = "M";
    private final String CHEAT_COMMAND = "C";
    private Grid grid;
    private GameUI gameUI;
    private final int NUMBER_OF_ROWS = 15;
    private final int NUMBER_OF_COLUMNS = 20;
    private int numberOfMonsters = 3;


    private ArrayList<Monster> monsters = new ArrayList<>();
    private Hero hero;
    private Power power;

    public GameLogic() {
        this.grid = new Grid();
        this.gameUI = new GameUI();

        initializeMonsters();
        //initialize Hero
        hero = new Hero(1, 1);
        grid.getCellObject(1, 1).setCellIsHero(true);
        spawnPower();

        gameUI.printStartUp();
        moveHero();

    }

    private void spawnPower() {
        Cell cell = grid.getRandomCellForPower();
        power = new Power(cell.getRowNumber(), cell.getColumnNumber());
        grid.getCellObject(power.getRowNumber(), power.getColumnNumber()).setCellIsPower(true);
        grid.getCellObject(power.getRowNumber(), power.getColumnNumber()).removeHideCell(true);
    }


    private void initializeMonsters() {
        monsters.add(new Monster(1, NUMBER_OF_COLUMNS - 2));
        monsters.add(new Monster(NUMBER_OF_ROWS - 2, NUMBER_OF_COLUMNS - 2));
        monsters.add(new Monster(NUMBER_OF_ROWS - 2, 1));

        grid.getCellObject(1, NUMBER_OF_COLUMNS - 2).incrementMonster();
        grid.getCellObject(1, NUMBER_OF_COLUMNS - 2).removeHideCell(true);
        grid.getCellObject(NUMBER_OF_ROWS - 2, NUMBER_OF_COLUMNS - 2).incrementMonster();
        grid.getCellObject(NUMBER_OF_ROWS - 2, NUMBER_OF_COLUMNS - 2).removeHideCell(true);
        grid.getCellObject(NUMBER_OF_ROWS - 2, 1).incrementMonster();
        grid.getCellObject(NUMBER_OF_ROWS - 2, 1).removeHideCell(true);

    }

    private void cellsAroundHero(){
        int row = hero.getRowNumber();
        int col = hero.getColumnNumber();
        grid.getCellObject(row, col).removeHideCell(true);

        if (grid.isCellPositionValid(row, col - 1)){
            grid.getCellObject(row, col - 1).removeHideCell(true);
        }
        if (grid.isCellPositionValid(row, col + 1)){
            grid.getCellObject(row, col + 1).removeHideCell(true);
        }
        if (grid.isCellPositionValid(row -1 , col)){
            grid.getCellObject(row -1 , col).removeHideCell(true);
        }
        if (grid.isCellPositionValid(row + 1, col)){
            grid.getCellObject(row + 1, col).removeHideCell(true);
        }

        //diagonal
        if (grid.isCellPositionValid(row + 1, col - 1)){
            grid.getCellObject(row + 1, col - 1).removeHideCell(true);
        }
        if (grid.isCellPositionValid(row + 1, col + 1)){
            grid.getCellObject(row + 1, col + 1).removeHideCell(true);
        }
        if (grid.isCellPositionValid(row - 1, col + 1)){
            grid.getCellObject(row - 1, col + 1).removeHideCell(true);
        }
        if (grid.isCellPositionValid(row - 1, col - 1)){
            grid.getCellObject(row - 1, col - 1).removeHideCell(true);
        }



    }
    private void setCornerVisible(){
        for (int j = 0; j < NUMBER_OF_ROWS; j++) {
            for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
                grid.getCellObject(0, i).removeHideCell(true);
                grid.getCellObject(j, 0).removeHideCell(true);
                grid.getCellObject(14, i).removeHideCell(true);
                grid.getCellObject(j, 19).removeHideCell(true);

            }
        }
    }

    private void moveHero() {

        while (true) {
            cellsAroundHero();
            setCornerVisible();
            gameUI.printHiddenMaze(grid);
            gameStatAfterEachMove();

            String input = gameUI.getHeroMoveInput();

            if (input.equals(HELP_COMMAND)) {
                gameUI.printHelpMenu();
                continue;
            }
            if (input.equals(REVEAL_MAZE_COMMAND)){
                gameUI.printMaze(grid);
            }
//            if (input.equals(CHEAT_COMMAND)){
//                numberOfMonsters = 1;
//            }

            if (!isInputValid(input)) {

                gameUI.printInvalidCommand();
            }
            int row = hero.getRowNumber();
            int col = hero.getColumnNumber();
            Cell heroCell = grid.getCellObject(row, col);
            grid.getCellObject(heroCell.getRowNumber(), heroCell.getColumnNumber()).setCellBlank();

            if (input.equals(UP_COMMAND)) {
                if (!isMoveValid(row - 1, col)) {
                    gameUI.printInvalidMove();
                    continue;
                }

                hero.goUp();


            } else if (input.equals(DOWN_COMMAND)) {
                if (!isMoveValid(row + 1, col)) {
                    gameUI.printInvalidMove();
                    continue;
                }

                hero.goDown();

            } else if (input.equals(RIGHT_COMMAND)) {
                if (!isMoveValid(row, col + 1)) {
                    gameUI.printInvalidMove();
                    continue;
                }

                hero.goRight();

            } else if (input.equals(LEFT_COMMAND)) {
                if (!isMoveValid(row, col - 1)) {
                    gameUI.printInvalidMove();
                    continue;
                }

                hero.goLeft();

            }

            heroCell.setCellIsHero(false);
            row = hero.getRowNumber();
            col = hero.getColumnNumber();
            Cell newHeroCell = grid.getCellObject(row, col);
            newHeroCell.setCellIsHero(true);

            moveMonsters();

            if (newHeroCell.isCellIsPower()) {
                hero.incrementPower();
                newHeroCell.setCellIsPower(false);
                power.decrementPowerCount();
                if (power.getPowerCount() == 0) {
                    spawnPower();
                }
            }

            while (newHeroCell.isCellIsMonster()) {
                if (hero.getPower() <= 0) {
                    gameUI.printHeroKilledMessage();
                    return;
                }
                hero.decrementPower();
                for (int i = 0; i < numberOfMonsters; i++) {
                    if (monsters.get(i).isAlive() && monsters.get(i).getRowNumber() == row && monsters.get(i).getColumnNumber() == col) {
                        monsters.get(i).killMonster();
                        numberOfMonsters -= 1;
                        //remove the monster from monsters list
//                        monsters.remove(i);
                        newHeroCell.decrementMonster();
                        break;
                    }
                }

            }

            boolean won = true;
            for (int i = 0; i < 3; i++) {
                if (monsters.get(i).isAlive()) {
                    won = false;
                    break;
                }

            }
            if (won) {
                gameUI.printWinMessage();
                return;
            }


        }

    }

    private boolean isInputValid(String userInput) {
        return (userInput.equals(UP_COMMAND) || userInput.equals(DOWN_COMMAND) ||
                userInput.equals(RIGHT_COMMAND) || userInput.equals(LEFT_COMMAND) ||
                userInput.equals(HELP_COMMAND));


    }

    private boolean isMoveValid(int row, int col) {
        if (!grid.isCellPositionValid(row, col)) {
            return false;
        }

        Cell cell = grid.getCellObject(row, col);
        return !cell.getWall();

    }

    private void moveMonsters() {
        for (int i = 0; i < numberOfMonsters; i++) {
            moveMonster(i);
        }
    }

    private void moveMonster(int i) {
        if (!monsters.get(i).isAlive()) return;
        ArrayList<String> commands = new ArrayList<String>(Arrays.asList(UP_COMMAND, DOWN_COMMAND, LEFT_COMMAND, RIGHT_COMMAND));
        int size = 4;
        int row = monsters.get(i).getRowNumber();
        int col = monsters.get(i).getColumnNumber();
        Cell monsterCell = grid.getCellObject(row, col);
        while (true) {
            Random rand = new Random();
            String input = commands.get(rand.nextInt(size));

            if (input.equals(UP_COMMAND)) {
                if (!isMoveValid(row - 1, col)) {
                    continue;
                }
                monsters.get(i).goUp();
                break;
            } else if (input.equals(DOWN_COMMAND)) {
                if (!isMoveValid(row + 1, col)) {
                    continue;
                }
                monsters.get(i).goDown();
                break;
            } else if (input.equals(RIGHT_COMMAND)) {
                if (!isMoveValid(row, col + 1)) {

                    continue;
                }
                monsters.get(i).goRight();
                break;
            } else if (input.equals(LEFT_COMMAND)) {
                if (!isMoveValid(row, col - 1)) {

                    continue;
                }
                monsters.get(i).goLeft();
                break;
            }

        }
        monsterCell.decrementMonster();
        int newRow = monsters.get(i).getRowNumber();
        int newCol = monsters.get(i).getColumnNumber();
        Cell newCell = grid.getCellObject(newRow, newCol);
        newCell.incrementMonster();

    }

    private void gameStatAfterEachMove() {

        gameUI.printGameStatAfterEachMove(hero.getPower(), numberOfMonsters);


    }


}
