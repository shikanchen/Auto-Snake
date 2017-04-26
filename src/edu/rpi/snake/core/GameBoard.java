package edu.rpi.snake.core;

import edu.rpi.snake.cons.BoardSize;
import edu.rpi.snake.cons.Direct;
import edu.rpi.snake.cons.TileType;
import edu.rpi.snake.main.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static edu.rpi.snake.cons.Direct.*;
import static java.lang.Thread.sleep;

/**
 * Created by Jerry Chen on 4/10/17.
 */
public class GameBoard implements Runnable{
    Main window;
    static Tile tiles[][];
    Point boardSize;

    private Snake snake;
    private static Tile food;

    private Agent agent;

    private static boolean stop = false;
    private static boolean suspend = true;

    public GameBoard(Main window, BoardSize size) {
        this.window = window;
        Point startPoint;
        switch (size) {
            case SMALL:
                boardSize = new Point(15, 15);
                break;
            case FAIR:
                boardSize = new Point(20, 20);
                break;
            case LARGE:
                boardSize = new Point(30, 30);
                break;
            case XLARGE:
                boardSize = new Point(40, 40);
                break;
            default:
                boardSize = new Point(15, 15);
                break;
        }
        tiles = new Tile[boardSize.x][boardSize.y];
        startPoint = new Point(boardSize.x/2, boardSize.y/2);

        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j< tiles[i].length; j++){
                tiles[i][j] = new Tile(TileType.BLANK);
                if (i == startPoint.x && j == startPoint.y) snake = new Snake(tiles[i][j]);
            }
        }
        GameBoard.generateFood();
        agent = makeNewRegular();
        setAgent(agent);
    }

    public boolean canMove(Direct direct) {
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
            while (!suspend) {
                try {
                    sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                agentActivate(agent);
            }
        }
    }

    public static void generateFood(){
        ArrayList<Tile> blanks = new ArrayList<>();
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j< tiles[i].length; j++){
                if (tiles[i][j].getType() == TileType.BLANK) blanks.add(tiles[i][j]);
            }
        }
        food = blanks.get(new Random().nextInt(blanks.size()));
        food.setType(TileType.FOOD);
    }

    private void agentActivate(Agent agent){
        synchronized (agent){
            agent.activate();
        }
    }

    public void setAgent(Agent agent){
        this.agent = agent;
    }

    public Agent getAgent() {
        return agent;
    }

    public Snake getSnake() {
        return snake;
    }

    public static Tile getFood() {
        return food;
    }

    public static void stop(){
        stop = true;
    }

    public static void pause(){
        suspend = true;
    }

    public static void resume(){
        suspend = false;
    }

    class Regular implements Agent {

        @Override
        public void activate() {
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

    public Regular makeNewRegular(){
        return new Regular();
    }
}
