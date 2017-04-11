package edu.rpi.snake.main;

import edu.rpi.snake.cons.BoardSize;
import edu.rpi.snake.core.Gameboard;
import edu.rpi.snake.gui.GameboardUI;

import javax.swing.*;


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

    Gameboard board = new Gameboard(BoardSize.XLARGE);

    public Main(){
        add(new GameboardUI(board));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new Main();

    }
}
