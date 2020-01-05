package com.company.Game.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by FAST on 10-May-18.
 */
public class DecoratedPanel extends JPanel{

    Image img;

    public DecoratedPanel() {
        img = new ImageIcon("maxresdefault.jpg").getImage();
    }

    public DecoratedPanel(LayoutManager layout) {
        super(layout);
        img = new ImageIcon("maxresdefault.jpg").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img , 0 , 0 ,this);

    }
}
