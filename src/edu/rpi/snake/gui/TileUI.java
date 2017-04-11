package edu.rpi.snake.gui;

import edu.rpi.snake.cons.TileType;
import edu.rpi.snake.core.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by Jerry Chen on 4/11/17.
 */
public class TileUI extends JComponent {
    HashMap<TileType, Color> colors = new HashMap<>();
    int side = 20;

    Tile tile;
    public TileUI(Tile tile) {
        colors.put(TileType.FOOD, Color.green);
        colors.put(TileType.SNAKE, Color.blue);
        colors.put(TileType.BLANK, Color.gray);

        this.tile = tile;
        setSize(side, side);
    }

    public void tilePos(int x, int y){
        setLocation(side*x, side*y);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(side, side);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(colors.get(tile.getType()));
        g.fillRoundRect(0, 0, side, side, side/5, side/5);
    }
}
