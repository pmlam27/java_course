package minesweeper;

import static org.junit.Assert.*;

public class MainTest {

    @org.junit.Test
    public void main() {
        GameMap gameMap = new GameMap();

        gameMap.setRandomMines(10);

    }
}