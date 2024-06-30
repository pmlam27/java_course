package minesweeper;

import static org.junit.Assert.*;

public class MainTest {

    @org.junit.Test
    public void main() {
        GameMap gameMap = new GameMap(4, 4, 4);
        //gameMap.logDebugMessage();
        gameMap.drawOnTerminal();
    }
}