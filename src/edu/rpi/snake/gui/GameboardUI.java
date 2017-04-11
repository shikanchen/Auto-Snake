package edu.rpi.snake.gui;

import edu.rpi.snake.core.Gameboard;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jerry Chen on 4/11/17.
 */
public class GameboardUI extends JComponent{
    Gameboard board;
    TileUI[][] tileUI;
    public GameboardUI(Gameboard board) {
        this.board = board;
        tileUI = new TileUI[board.getBoardSize().x][board.getBoardSize().y];
        for (int i = 0; i < tileUI.length; i++) {
            for (int j = 0; j < tileUI[i].length; j++) {
                tileUI[i][j] = new TileUI(board.getTile(i, j));
                tileUI[i][j].tilePos(i, j);
                add(tileUI[i][j]);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(tileUI[0][0].getPreferredSize().width * tileUI.length, tileUI[0][0].getPreferredSize().width * tileUI[0].length);
    }

    @Override
    public void paintComponent(Graphics g) {
//        tileUI
        g.setColor(Color.black);    //Set background color
        g.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);  //Fill background
    }
}
