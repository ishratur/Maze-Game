package ca.cmpt213.asn2.Model;


import ca.cmpt213.asn2.UI.GameUI;

import java.util.*;

public class GameLogic {
    private final String UP_COMMAND = "W";
    private final String DOWN_COMMAND = "S";
    private final String LEFT_COMMAND = "A";
    private final String RIGHT_COMMAND = "D";
    private final String HELP_COMMAND = "?";
    private final String REVEAL_MAZE_COMMAND = "m";
    private Grid grid;
    private GameUI gameUI;
    private final int NUMBER_OF_ROWS = 15;
    private final int NUMBER_OF_COLUMNS = 20;


    private ArrayList<Monster> monsters = new ArrayList<>();
    private Hero hero;
    private Power power;

    public GameLogic() {
        this.grid = new Grid();
        this.gameUI = new GameUI();

        initializeMonsters();
        //initialize Hero
        hero = new Hero(1,1);
        grid.getCellObject(1,1).setCellIsHero(true);
        //initialize power
        Cell cell = grid.getRandomCellForPower();
        power = new Power(cell.getRowNumber(),cell.getColumnNumber());
        grid.getCellObject(power.getRowNumber(), power.getColumnNumber()).setCellIsPower(true);
        initializeMonsters();
        gameUI.printStartUp();
        gameUI.printMaze(grid);
        moveHero();

    }


    public void initializeMonsters(){
        monsters.add(new Monster(1, NUMBER_OF_COLUMNS - 2 ));
        monsters.add(new Monster(NUMBER_OF_ROWS - 2, NUMBER_OF_COLUMNS - 2 ));
        monsters.add(new Monster(NUMBER_OF_ROWS -2, 1 ));

        grid.getCellObject(1,NUMBER_OF_COLUMNS - 2 ).incrementMonster();
        grid.getCellObject(NUMBER_OF_ROWS - 2,NUMBER_OF_COLUMNS - 2 ).incrementMonster();
        grid.getCellObject(NUMBER_OF_ROWS -2,1 ).incrementMonster();

    }

    public void moveHero(){

        while (true){
            Scanner userInput = new Scanner(System.in);
            String input = userInput.next().toUpperCase();
            if (!isInputValid(input)){

                System.out.println("Invalid user input");
            }
            int row = hero.getRowNumber();
            int col = hero.getColumnNumber();
            Cell heroCell = grid.getCellObject(row, col);

            if (input.equals(UP_COMMAND)){
                if (!isMoveValid(row -1, col)){
                    System.out.println("Invalid move! You can not move through walls");
                    continue;
                }
                hero.goUp();


            }
            else if (input.equals(DOWN_COMMAND)){
                if (!isMoveValid(row + 1, col)){
                    System.out.println("Invalid move! You can not move through walls");
                    continue;
                }
                hero.goDown();

            }
            else if (input.equals(RIGHT_COMMAND)){
                if (!isMoveValid(row , col + 1)){
                    System.out.println("Invalid move! You can not move through walls");
                    continue;
                }
                hero.goRight();

            }
            else if (input.equals(LEFT_COMMAND)) {
                if (!isMoveValid(row, col - 1)) {
                    System.out.println("Invalid move! You can not move through walls");
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

            if(newHeroCell.isCellIsPower()) {
                hero.incrementPower();
                newHeroCell.setCellIsPower(false);
            }

            while(newHeroCell.isCellIsMonster()) {
                if(hero.getPower() <= 0) {
                    System.out.println("Monster killed you!");
                    return;
                }
                hero.decrementPower();
                for(int i = 0; i < 3; i++) {
                    if(monsters.get(i).isAlive() && monsters.get(i).getRowNumber() == row && monsters.get(i).getColumnNumber() == col) {
                        monsters.get(i).killMonster();
                        newHeroCell.decrementMonster();
                        break;
                    }
                }

            }

            boolean won = true;
            for(int i = 0; i < 3; i++) {
                if(monsters.get(i).isAlive()) {
                    won = false;
                    break;
                }

            }
            if(won) {
                System.out.println("Yayy!! You won the game!");
                return;
            }


        }



    }

    public boolean isInputValid(String userInput){
        return (userInput.equals(UP_COMMAND) || userInput.equals(DOWN_COMMAND) ||
                userInput.equals(RIGHT_COMMAND) || userInput.equals(LEFT_COMMAND) ||
                userInput.equals(HELP_COMMAND));


    }

    public boolean isMoveValid(int row, int col){
        if (!grid.isCellPositionValid(row, col)){
            return false;
        }

        Cell cell = grid.getCellObject(row, col);
        return !cell.getWall();

    }

    public void moveMonsters(){
        for (int i = 0; i < 3; i++) {
            moveMonster(i);
        }
    }

    public void moveMonster(int i) {
        if(!monsters.get(i).isAlive()) return;
        ArrayList<String> commands = new ArrayList<String>(Arrays.asList(UP_COMMAND, DOWN_COMMAND, LEFT_COMMAND, RIGHT_COMMAND));
        int size = 4;
        int row = monsters.get(i).getRowNumber();
        int col = monsters.get(i).getColumnNumber();
        Cell  monsterCell = grid.getCellObject(row, col);
        while(true) {
            Random rand = new Random();
            String input = commands.get(rand.nextInt(size));

            if (input.equals(UP_COMMAND)){
                if (!isMoveValid(row -1, col)){
                    continue;
                }
                monsters.get(i).goUp();
                break;
            }
            else if (input.equals(DOWN_COMMAND)){
                if (!isMoveValid(row + 1, col)){
                    continue;
                }
                monsters.get(i).goDown();
                break;
            }
            else if (input.equals(RIGHT_COMMAND)){
                if (!isMoveValid(row , col + 1)){

                    continue;
                }
                monsters.get(i).goRight();
                break;
            }
            else if (input.equals(LEFT_COMMAND)){
                if (!isMoveValid(row , col - 1)){

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




}
