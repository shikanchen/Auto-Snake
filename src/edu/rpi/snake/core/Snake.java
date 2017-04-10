package edu.rpi.snake.core;

import edu.rpi.snake.cons.TileType;

import java.util.ArrayList;

/**
 * Created by Jerry Chen on 4/10/17.
 */
public class Snake extends ArrayList<Tile> {
    private int length = 0;

    public Snake() {
        super();
    }

    private void moveAndGrow(Tile tile) {
        this.add(tile);
        tile.setType(TileType.SNAKE);
    }

    private void moveAndStop(Tile tile) {
        this.add(tile);
        tile.setType(TileType.SNAKE);
        this.remove(0);
    }

    private void stop() {

    }

    public void Move(Tile tile) {
        switch (tile.getType()) {
            case BLANK: moveAndStop(tile);
            case FOOD: moveAndGrow(tile);
            case SNAKE: stop();
            default: stop();
        }
    }
}