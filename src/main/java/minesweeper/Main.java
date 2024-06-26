package minesweeper;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner stdIn = new Scanner(System.in);

        System.out.print("How many mines do you want on the field?");
        final int mineCount = stdIn.nextInt();

    }
}

class Cell {
    private boolean containingMine;
    private boolean revealed;
    private int adjacentMineCount;

    Cell(boolean containingMine, boolean revealed) {
        this.containingMine = containingMine;
        this.revealed = revealed;
    }

    boolean isContainingMine() { return containingMine; }
    boolean isRevealed() { return revealed; }
    int getAdjacentMineCount() { return adjacentMineCount; }
    void setMineActive() { containingMine = true; }
}

class GameMap {

    final int mapRows;
    final int mapColumns;
    final int cellCount;

    private final Cell[][] cellMatrix;

    GameMap(int mapRows, int mapColumns) {
        this.mapRows = mapRows;
        this.mapColumns = mapColumns;
        this.cellCount = mapRows * mapColumns;

        cellMatrix = new Cell[mapRows][mapColumns];

        for (int i=0; i<mapRows; i++) {
            for (int j=0; j<mapColumns; j++) {
                cellMatrix[i][j] = new Cell(false, true);
            }
        }
    }

    void drawOnTerminal() {
        for(final Cell[] cellRow : cellMatrix) {
            for (final Cell cell : cellRow) {
                System.out.print(cell.isContainingMine() ? "X" : ".");
            }
            System.out.print("\n");
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
            final Cell cellToChange = cellMatrix[mineRow][mineColumn];
            cellToChange.setMineActive();
        }
    }

    void logDebugMessage() {
        for(final Cell[] cellRow : cellMatrix) {
            System.out.print("{");
            for (final Cell cell : cellRow) {
                System.out.print(" [");
                System.out.print(cell.isContainingMine() ? "X" : ".");
                System.out.print(" ");
                System.out.print(cell.isRevealed() ? "open" : "hidden");
                System.out.print("] ");
            }
            System.out.print("}\n");
        }
    }
}
