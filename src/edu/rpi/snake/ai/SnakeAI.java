package edu.rpi.snake.ai;

import edu.rpi.snake.cons.Direct;
import edu.rpi.snake.cons.TileType;
import edu.rpi.snake.core.GameBoard;
import edu.rpi.snake.core.Snake;
import edu.rpi.snake.core.Agent;

import java.awt.*;

/**
 * Created by Jerry Chen on 4/13/17.
 */
public class SnakeAI implements Agent {

    GameBoard board;
    Snake snake;

    public SnakeAI(GameBoard board) {
        this.board = board;
        this.snake = board.getSnake();
    }

    private Direct findDirect(){
        Point head = board.getPosition(snake.getHead());
        Point tail = board.getPosition(snake.getTail());
        Point target = board.getPosition(board.getFood());
//      TODO implement every condition
        System.out.println(board.getPosition(snake.getHead()));
        if (isReachable(head, target)){
//            Can eat food directly
            System.out.println("isReachable");
        }else if (isRoopable(head, tail)){
//            Can reach the tail directly
            System.out.println("isRoopable");
        }else{
            System.out.println("cannot find a smart way");
//            Filling tile as many as possible
        }

        return Direct.LEFT;
    }

    private boolean isReachable(Point starting, Point target){
        if (starting.x > target.x){
           starting.translate(-1, 0);
        }else {
            starting.translate(1, 0);
        }

        if (starting.y > target.y){
            starting.translate(0, -1);
        }else {
            starting.translate(0, 1);
        }
        return isBlocked(starting, target);
    }

    private boolean isRoopable(Point head, Point tail){
        if (head.x > tail.x){
            head.translate(-1, 0);
            tail.translate(1, 0);
        }else {
            head.translate(1, 0);
            tail.translate(-1, 0);
        }

        if (head.y > tail.y){
            head.translate(0, -1);
            tail.translate(0, 1);
        }else {
            head.translate(0, 1);
            tail.translate(0, -1);
        }
        return isBlocked(head, tail);
    }

    private boolean isBlocked(Point starting, Point target){
        Point x = starting.x < target.x?new Point(starting.x, target.x):new Point(target.x, starting.x);
        Point y = starting.y < target.y?new Point(starting.y, target.y):new Point(target.y, starting.y);

        boolean isBlocked = false;
        for (int i = x.x; i < x.y; i++){
            for (int j = y.x; j< y.y; j++){
                if (board.getTile(i,j).getType() == TileType.SNAKE)  isBlocked = true;
            }
            if (!isBlocked) break;
        }

        if (!isBlocked) {
            for (int i = y.x; i < y.y; i++){
                for (int j = x.x; j< x.y; j++){
                    if (board.getTile(j,i).getType() == TileType.SNAKE)  isBlocked = true;
                }
                if (!isBlocked) break;
            }
        }

        return isBlocked;
    }

    @Override
    public void activate() {
        snake.setDirection(findDirect());
        board.setAgent(board.makeNewRegular());
    }
}
