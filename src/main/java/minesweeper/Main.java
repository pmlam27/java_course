package minesweeper;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner stdIn = new Scanner(System.in);

        System.out.print("How many mines do you want on the field?");
        final int mineCount = stdIn.nextInt();
        GameMap gameMap = new GameMap(9, 9, mineCount);
        gameMap.drawOnTerminal();
    }
}

class Displacement {
    final int row;
    final int column;

    Displacement(int row, int column) {
        this.row = row;
        this.column = column;
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

        Displacement[] AdjacentDisplacements = {
                new Displacement(1, 1),
                new Displacement(1, 0),
                new Displacement(1, -1),
                new Displacement(0, 1),
                new Displacement(0, -1),
                new Displacement(-1, 1),
                new Displacement(-1, 0),
                new Displacement(-1, -1),
        };

        for (int rowIndex=0; rowIndex < mapRows; rowIndex++) {
            for (int columnIndex=0; columnIndex < mapColumns; columnIndex++) {
                if (cellMatrix[rowIndex][columnIndex] != null) { continue; }

                int adjacentMineCounter = 0;
                for (Displacement displacement : AdjacentDisplacements) {
                    int newRowIndex = rowIndex + displacement.row;
                    int newColumnIndex = columnIndex + displacement.column;
                    boolean adjacentPositionOutOfScope =    (newRowIndex > mapRows-1) || (newRowIndex < 0) ||
                                                            (newColumnIndex > mapColumns-1) || (newColumnIndex < 0);
                    if (adjacentPositionOutOfScope) { continue; }

                    Cell validAdjacentCell = cellMatrix[newRowIndex][newColumnIndex];
                    if(validAdjacentCell == null) { continue; }

                    if (validAdjacentCell instanceof MineCell) {
                        adjacentMineCounter++;
                    }
                }
                cellMatrix[rowIndex][columnIndex] = new EmptyCell(true, adjacentMineCounter);
            }
        }

    }

    void drawOnTerminal() {
        for(final Cell[] cellRow : cellMatrix) {
            for (final Cell cell : cellRow) {
                if (cell instanceof EmptyCell) {
                    int mineCount = ((EmptyCell) cell).getAdjacentMineCount();
                    System.out.print((mineCount == 0) ? "." : mineCount);
                } else {
                    System.out.print("X");
                }
            }
            System.out.print("\n");
        }
    }

    void logDebugMessage() {
        for(final Cell[] cellRow : cellMatrix) {
            for (final Cell cell : cellRow) {
                System.out.print(cell);
                System.out.print(" ");
            }
            System.out.println();
        }

        for(final Cell[] cellRow : cellMatrix) {
            System.out.print("{");
            for (final Cell cell : cellRow) {

                System.out.print(" ");
                System.out.print(cell.isRevealed() ? " " : "H");
                System.out.print(" ");
                if (cell instanceof EmptyCell) {
                    System.out.print("[" + ((EmptyCell) cell).getAdjacentMineCount() + "]");
                } else {
                    System.out.print("M");
                }
                System.out.print("\t");
            }
            System.out.print("}\n");
        }
    }
}
