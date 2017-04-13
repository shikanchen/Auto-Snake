package edu.rpi.snake.core;

import edu.rpi.snake.cons.Direct;
import edu.rpi.snake.cons.TileType;

import java.util.ArrayList;

/**
 * Created by Jerry Chen on 4/10/17.
 */
public class Snake extends ArrayList<Tile> {

    private Direct heading;

    public Snake(Tile startPoint) {
        init(startPoint);
    }

    private void init(Tile tile) {
        heading = Direct.UP;
        add(tile);
        tile.setType(TileType.SNAKE);
    }

    private void moveAndGrow(Tile tile) {
        add(tile);
        tile.setType(TileType.SNAKE);
        GameBoard.generateFood();
    }

    private void moveAndStop(Tile tile) {
        add(tile);
        tile.setType(TileType.SNAKE);
        getTail().setType(TileType.BLANK);
        remove(0);

    }

    private void stop() {
        GameBoard.stop();
    }

    public void move(Tile tile) {
        switch (tile.getType()) {
            case BLANK:
                moveAndStop(tile);
                break;
            case FOOD:
                moveAndGrow(tile);
                break;
            case SNAKE:
                stop();
                break;
            default:
                stop();
                break;
        }
    }

    public Tile getHead() {
        return get(size()-1);
    }

    public  Tile getTail() { return get(0); }

    public Direct getDirection(){
        return heading;
    }

    public void setDirection(Direct direct){
        heading = direct;
    }

    public int length(){
        return size();
    }
}
