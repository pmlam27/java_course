package minesweeper;

import java.util.Scanner;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Scanner stdIn = new Scanner(System.in);

        GameMap gameMap = new GameMap();

        System.out.print("How many mines do you want on the field?");
        final int mineCount = stdIn.nextInt();
        gameMap.setRandomMines(mineCount);
        gameMap.print();

    }
}

class GameMap {
    static final int mapRows = 9;
    static final int mapColumns = 9;
    static final int cellCount = mapRows * mapColumns;

    private char[][] charMatrix;

    GameMap() {
        charMatrix = new char[mapRows][mapColumns];
        for (int i=0; i<mapRows; i++) {
            for (int j=0; j<mapColumns; j++) {
                charMatrix[i][j] = '.';
            }
        }
    }

    void setRandomMines(int mineCount) {
        Random randomGenerator = new Random();

        Set<Integer> randomSet = new HashSet<>();

        while(randomSet.size() < mineCount) {
            final int nextRandom = randomGenerator.nextInt(cellCount);
            randomSet.add(nextRandom);
        }

        for(final int randomNum : randomSet) {
            final int mineRow = randomNum / mapColumns;
            final int mineColumn = randomNum % mapColumns;
            charMatrix[mineRow][mineColumn] = 'X';
        }
    }

    void print() {
        for (char[] column: charMatrix) {
            for (char letter: column) {
                System.out.print(letter);
            }
            System.out.print("\n");
        }
    }
}
