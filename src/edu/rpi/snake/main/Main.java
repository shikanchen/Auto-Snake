package edu.rpi.snake.main;

import edu.rpi.snake.ai.SnakeAI;
import edu.rpi.snake.cons.BoardSize;
import edu.rpi.snake.cons.Direct;
import edu.rpi.snake.core.GameBoard;
import edu.rpi.snake.gui.GameBoardUI;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Created by Jerry Chen on 4/10/17.
 */
public class Main extends JFrame{

    static{
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    GameBoard board = new GameBoard(this, BoardSize.XLARGE);

    public Main(){
        add(new GameBoardUI(board));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case 37:
                        board.turn(Direct.LEFT);
                        break;
                    case 38:
                        board.turn(Direct.UP);
                        break;
                    case 39:
                        board.turn(Direct.RIGHT);
                        break;
                    case 40:
                        board.turn(Direct.DOWN);
                        break;
                }
                super.keyPressed(e);
            }
        });

//        TODO Setup Agent Toggle
        new SnakeAI(board, board.getSnake());

        board.resume();
        new Thread(board).start();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new Main();

    }
}
