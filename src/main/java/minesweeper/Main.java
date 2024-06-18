package minesweeper;

public class Main {

    public static void main(String[] args) {
        Map gameMap = new Map(9, 9);
        gameMap.setMine();
        gameMap.print();
    }
}

class Map {
    private char[][] charMatrix;

    Map(int rows, int columns) {
        charMatrix = new char[rows][columns];
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                charMatrix[i][j] = '.';
            }
        }
    }

    void setMine() {
        charMatrix[0][0] = 'X';
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