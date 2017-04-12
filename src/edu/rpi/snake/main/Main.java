package edu.rpi.snake.main;

import edu.rpi.snake.cons.BoardSize;
import edu.rpi.snake.cons.Direct;
import edu.rpi.snake.core.Gameboard;
import edu.rpi.snake.gui.GameboardUI;

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

    Gameboard board = new Gameboard(this, BoardSize.XLARGE);

    public Main(){
        add(new GameboardUI(board));
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
