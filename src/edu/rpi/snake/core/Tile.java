package edu.rpi.snake.core;

import edu.rpi.snake.cons.TileType;

/**
 * Created by Jerry Chen on 4/10/17.
 */
public class Tile {
    private TileType type;

    public Tile(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }
}
