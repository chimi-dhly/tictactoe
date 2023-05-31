package tictactoe;

import java.util.Objects;

public class Game {
    private final int size = 3;
    private final Square[] grid;
    private GameState state;
    public Game() {
        this.grid = new Square[size*size];
        for (int i = 0; i < size * size; i++) {
            grid[i] = Square.EMPTY;
        }
        setMainState();
    }

    private void setMainState() {
        state = GameState.XTURN;
        printGame();
    }

    private void printGame() {
        for (int dot = 0; dot < 9; dot++) {
            System.out.print("-");
        }
        System.out.print("\n");
        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                System.out.printf("%c ",grid[i*size+j].getState());
            }
            System.out.print("|\n");
        }
        for (int dot = 0; dot < 9; dot++) {
            System.out.print("-");
        }
        System.out.print("\n");
    }

    public boolean isRunning() {
        return state != GameState.COMPLETE;
    }

    /*------------------------------------GROUP OF METHODS TO CHECK IF THE GAME IS WON ------------------*/
    private void checkTheStateOfTheGame() {
        if (isWonByX()) {
            System.out.println("X wins");
            state = GameState.COMPLETE;
        }
        else if (isWonByO()) {
            System.out.println("O wins");
            state = GameState.COMPLETE;
        }
        else if (isDraw()) {
            System.out.println("Draw");
            state = GameState.COMPLETE;
        }
    }

    private boolean isDraw() {
        return (!hasEmptyCell() && (!isWonByO()) && !isWonByX());
    }
    private boolean isWonByX() {
        return numberOfOInARow() == 0 && numberOfXInARow() == 1;
    }
    private boolean isWonByO() {
        return numberOfXInARow() == 0 && numberOfOInARow() == 1;
    }
    private boolean hasEmptyCell() {
        for (Square element:
                grid) {
            if (element == Square.EMPTY) {
                return true;
            }
        }
        return false;
    }
    private int numberOfXInARow() {
        //check the columns
        int xCounter = 0;
        for (int i = 0; i < size; i++) {
            if (grid[i] == Square.X && grid[i + size] == Square.X && grid[i + 2 * size] == Square.X) {
                xCounter++;
            }
        }
        //check the lines
        for (int i = 0; i < size; i++) {
            if (grid[i * size] == Square.X && grid[i * size + 1] == Square.X && grid[i*size +2] == Square.X) {
                xCounter++;
            }
        }
        //Check the diagonals
        if (grid[0] == Square.X && grid[4] == Square.X && grid[8] == Square.X) {
            xCounter++;
        }
        if (grid[2] == Square.X && grid[4] == Square.X && grid[6] == Square.X) {
            xCounter++;
        }
        return xCounter;
    }
    private int numberOfOInARow() {
        //check the columns
        int oCounter = 0;
        for (int i = 0; i < size; i++) {
            if (grid[i] == Square.O && grid[i + size] == Square.O && grid[i + 2 * size] == Square.O) {
                oCounter++;
            }
        }
        //check the lines
        for (int i = 0; i < size; i++) {
            if (grid[i * size] == Square.O && grid[i * size + 1] == Square.O && grid[i*size +2] == Square.O) {
                oCounter++;
            }
        }
        //Check the diagonals
        if (grid[0] == Square.O && grid[4] == Square.O && grid[8] == Square.O) {
            oCounter++;
        }
        if (grid[2] == Square.O && grid[4] == Square.O && grid[6] == Square.O) {
            oCounter++;
        }
        return oCounter;
    }

/*-------------------------------------------------STAGE 4/5-----------------------------------------*/

    public void isInputFalse(String s) {
        //convert to string array
        String[] stringArrayOfCoordinates = s.split(" ");
        //check if user has input
        if (stringArrayOfCoordinates.length != 2) {
            System.out.println("You should only enter 2 inputs!");
            return;
        }
        //convert to an int array
        int[] intArrayOfCoordinates = new int[2];
        try {
            intArrayOfCoordinates[0] = Integer.parseInt(stringArrayOfCoordinates[0]);
            intArrayOfCoordinates[1] = Integer.parseInt(stringArrayOfCoordinates[1]);
        } catch (Exception e) {
            System.out.println("You should only enter numbers!");
            return;
        }
        //check if the entered coordinates are inside the grid
        for (int coordinate: intArrayOfCoordinates){
            if (coordinate < 1 || coordinate > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                return;
            }
        }
        //check if the square is occupied
        int i = (intArrayOfCoordinates[0] - 1) * size + intArrayOfCoordinates[1];
        if (grid[i -1] != Square.EMPTY) {
            System.out.println("This square is occupied! Choose another one!");
            return;
        }
        if (state == GameState.XTURN) {
            grid[i -1] = Square.X;
            state = GameState.OTURN;
        } else if (state == GameState.OTURN) {
            grid[i -1] = Square.O;
            state = GameState.XTURN;
        }
    }

    /*---------------------------------------- STAGE 5/5 ------------------------------------------------*/

    public void execute(String s) {
        isInputFalse(s);
        printGame();
        checkTheStateOfTheGame();
    }
}

