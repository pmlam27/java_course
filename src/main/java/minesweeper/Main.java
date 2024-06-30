package minesweeper;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner stdIn = new Scanner(System.in);

        System.out.print("How many mines do you want on the field?");
        final int mineCount = stdIn.nextInt();
        GameMap gameMap = new GameMap(10, 10, 10);
        gameMap.drawOnTerminal();
    }
}

interface Cell {
    boolean isRevealed();
}

class MineCell implements Cell {
    private boolean revealed;

    MineCell(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean isRevealed() { return revealed; }
}

class EmptyCell implements Cell {
    private boolean revealed;
    private final int adjacentMineCount;

    EmptyCell(boolean revealed, int adjacentMineCount) {
        this.revealed = revealed;
        this.adjacentMineCount = adjacentMineCount;
    }

    public boolean isRevealed() { return revealed; }
    int getAdjacentMineCount() {
        return adjacentMineCount;
    }
}

class GameMap {

    final int mapRows;
    final int mapColumns;
    final int cellCount;

    private final Cell[][] cellMatrix;

    GameMap(int mapRows, int mapColumns, int mineCount) {
        this.mapRows = mapRows;
        this.mapColumns = mapColumns;
        this.cellCount = mapRows * mapColumns;

        cellMatrix = new Cell[mapRows][mapColumns];

        // generate random mines
        Set<Integer> randomSet = new HashSet<>();
        Random randomGenerator = new Random();
        while(randomSet.size() < mineCount) {
            final int nextRandom = randomGenerator.nextInt(cellCount);
            randomSet.add(nextRandom);
        }
        for(final int randomNum : randomSet) {
            final int mineRow = randomNum / mapColumns;
            final int mineColumn = randomNum % mapColumns;
            cellMatrix[mineRow][mineColumn] = new MineCell(true);
        }

        // compute adjacent mine count and fill remaining with empty cell
        for (int i=0; i<mapRows; i++) {
            for (int j=0; j<mapColumns; j++) {
                if (cellMatrix[i][j] != null) { continue; }


                //cellMatrix[i][j] = new EmptyCell(true);
            }
        }

    }

    void drawOnTerminal() {
        for(final Cell[] cellRow : cellMatrix) {
            for (final Cell cell : cellRow) {
                System.out.print((cell instanceof MineCell) ? "X" : ".");
            }
            System.out.print("\n");
        }
    }

    void logDebugMessage() {
        for(final Cell[] cellRow : cellMatrix) {
            System.out.print("{");
            for (final Cell cell : cellRow) {

                System.out.print(" ");
                System.out.print(cell.isRevealed() ? " " : "H");
                System.out.print(" ");
                System.out.print((cell instanceof MineCell) ? "M" : "[]");
                System.out.print("\t");
            }
            System.out.print("}\n");
        }
    }
}
