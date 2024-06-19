package minesweeper;

import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Scanner stdIn = new Scanner(System.in);

        System.out.print("How many mines do you want on the field?");
        final int mineCount = stdIn.nextInt();

        Map gameMap = new Map();

        gameMap.setRandomMines(mineCount);
        gameMap.print();
    }
}

class Map {
    static final int mapRows = 9;
    static final int mapColumns = 9;
    static final int cellCount = mapRows * mapColumns;

    private char[][] charMatrix;

    Map() {
        charMatrix = new char[mapRows][mapColumns];
        for (int i=0; i<mapRows; i++) {
            for (int j=0; j<mapColumns; j++) {
                charMatrix[i][j] = '.';
            }
        }
    }

    void setRandomMines(int mineCount) {
        Random randomGenerator = new Random();

        for(int i=0; i<mineCount; i++) {
            final int mineIndex = randomGenerator.nextInt(mineCount);
            final int mineRow = mineIndex / mapColumns;
            final int mineColumn = mineIndex % mapColumns;
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
