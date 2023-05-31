package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        while (game.isRunning()) {
            game.execute(scanner.nextLine());
        }


    }
}