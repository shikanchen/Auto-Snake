package edu.rpi.snake.core;

import edu.rpi.snake.cons.BoardSize;
import edu.rpi.snake.cons.TileType;

/**
 * Created by Jerry Chen on 4/10/17.
 */
public class Gameboard {
    Tile tiles[][];

    public Gameboard(BoardSize size) {
        switch (size) {
            case SMALL: tiles = new Tile[5][5];
            case FAIR: tiles = new Tile[10][10];
            case LARGE: tiles = new Tile[15][15];
            case XLARGE: tiles = new Tile[20][20];
        }

        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j< tiles[i].length; j++){
                tiles[i][j] = new Tile(TileType.BLANK);
            }
        }
    }

    public boolean canMove(Tile tile) {
//        TODO implement canMove: cannot change direction to the opposite one of the current.
        return false;
    }
}
