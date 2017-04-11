package edu.rpi.snake.core;

import edu.rpi.snake.cons.BoardSize;
import edu.rpi.snake.cons.Direct;
import edu.rpi.snake.cons.TileType;

import java.awt.*;

import static edu.rpi.snake.cons.Direct.*;

/**
 * Created by Jerry Chen on 4/10/17.
 */
public class Gameboard {
    Tile tiles[][];
    Point boardSize;
    Snake snake;

    public Gameboard(BoardSize size) {
        Point startPoint;
        switch (size) {
            case SMALL:
                boardSize = new Point(5, 5);
                break;
            case FAIR:
                boardSize = new Point(10, 10);
                break;
            case LARGE:
                boardSize = new Point(15, 15);
                break;
            case XLARGE:
                boardSize = new Point(20, 20);
                break;
            default:
                boardSize = new Point(15, 15);
        }
        tiles = new Tile[boardSize.x][boardSize.y];
        startPoint = new Point(boardSize.x/2, boardSize.y/2);

        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j< tiles[i].length; j++){
                tiles[i][j] = new Tile(TileType.BLANK);
                if (i == startPoint.x && j == startPoint.y) snake = new Snake(tiles[i][j]);
            }
        }
    }

    public boolean canMove(Direct direct) {
        if (snake.size() >= 2){
            switch (getDirection(new Direct[]{UP, RIGHT, DOWN, LEFT})){
                case UP:
                    if (direct == DOWN) return false;
                    break;
                case RIGHT:
                    if (direct == LEFT) return false;
                    break;
                case DOWN:
                    if (direct == UP) return false;
                    break;
                case LEFT:
                    if (direct == RIGHT) return false;
                    break;
            }
            return true;
        }
        return true;
    }

    public void move(Tile tile){
        snake.move(tile);
    }

    public Point getPosition(Tile tile) {
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j< tiles[i].length; j++){
                if (tiles[i][j].equals(tile)) return new Point(i, j);
            }
        }
        return new Point(-1, -1);
    }

    public Direct getDirection(Direct[] direct){
        if (direct.length == 1) return direct[0];
        Point last = getPosition(snake.get(snake.size() - 1));
        Point last2 = getPosition(snake.get(snake.size() - 2));
        switch (direct[0]){
            case UP:
                last2.translate(0, 1);
                if (last.equals(last2)) return UP;
                break;
            case RIGHT:
                last2.translate(1, 0);
                if (last.equals(last2)) return RIGHT;
                break;
            case DOWN:
                last2.translate(0, -1);
                if (last.equals(last2)) return DOWN;
                break;
            case LEFT:
                last2.translate(-1, 0);
                if (last.equals(last2)) return LEFT;
                break;
        }
        Direct[] tmp = new Direct[direct.length - 1];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = direct[i+1];
        }
        return getDirection(tmp);
    }

    public Point getBoardSize(){
        return boardSize;
    }

    public Tile getTile(int x, int y){
        return tiles[x][y];
    }

}
