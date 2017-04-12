package edu.rpi.snake.core;

import edu.rpi.snake.cons.BoardSize;
import edu.rpi.snake.cons.Direct;
import edu.rpi.snake.cons.TileType;
import edu.rpi.snake.main.Main;

import java.awt.*;

import static edu.rpi.snake.cons.Direct.*;
import static java.lang.Thread.sleep;

/**
 * Created by Jerry Chen on 4/10/17.
 */
public class Gameboard implements Runnable{
    Main window;
    Tile tiles[][];
    Point boardSize;
    Snake snake;

    boolean stop = false;
    boolean suspend = true;

    public Gameboard(Main window, BoardSize size) {
        this.window = window;
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
        if (snake.length() >= 2){
            switch (snake.getDirection()){
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

    public void turn(Direct direct) {
        if (canMove(direct)) snake.setDirection(direct);
    }

    public Point getPosition(Tile tile) {
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j< tiles[i].length; j++){
                if (tiles[i][j].equals(tile)) return new Point(i, j);
            }
        }
        return new Point(-1, -1);
    }

    public Point getBoardSize(){
        return boardSize;
    }

    public Tile getTile(int x, int y){
        return tiles[x][y];
    }

    @Override
    public void run() {
        while (!stop){
            while (!suspend){
                try {
                    sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (canMove(snake.getDirection())){
                    Point lp = getPosition(snake.getHead());
                    switch (snake.getDirection()){
                        case LEFT:
                            lp.translate(-1, 0);
                            break;
                        case UP:
                            lp.translate(0, -1);
                            break;
                        case RIGHT:
                            lp.translate(1, 0);
                            break;
                        case DOWN:
                            lp.translate(0, 1);
                            break;
                    }
                    snake.move(tiles[lp.x][lp.y]);
                }
                window.repaint();
            }
        }
    }

    public void stop(){
        stop = true;
    }

    public void pause(){
        suspend = true;
    }

    public void resume(){
        suspend = false;
    }
}
