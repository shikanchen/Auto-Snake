package edu.rpi.snake.ai;

import edu.rpi.snake.cons.Direct;
import edu.rpi.snake.core.GameBoard;
import edu.rpi.snake.core.Snake;
import edu.rpi.snake.core.Agent;

/**
 * Created by Jerry Chen on 4/13/17.
 */
public class SnakeAI implements Agent {

    GameBoard board;
    Snake snake;

    public SnakeAI(GameBoard board, Snake snake) {
        this.board = board;
        this.snake = snake;
    }

    private Direct findDirect(){
        return Direct.LEFT;
    }

    @Override
    public void activate() {
        snake.setDirection(findDirect());
        board.setAgent(board.makeNewRegular());
    }
}
