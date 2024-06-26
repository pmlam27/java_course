package minesweeper;

import static org.junit.Assert.*;

public class MainTest {

    @org.junit.Test
    public void main() {
        GameMap gameMap = new GameMap(9, 9);
        gameMap.logDebugMessage();
        gameMap.drawOnTerminal();
        System.out.println();
        gameMap.setRandomMines(10);
        gameMap.drawOnTerminal();
    }
}