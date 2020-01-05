package com.company.Game.model;

import javax.swing.*;
import java.awt.*;

public class HealthBar extends JPanel{
    private final int min = 0 ;
    private final int max = 100 ;
    private Player player = Player.getInstance();

    public JProgressBar getJpBar() {
        return jpBar;
    }

    JProgressBar jpBar;
    private static final HealthBar instance = new HealthBar();

    public static HealthBar getInstance()
    {
        return instance;
    }

    private HealthBar() {
        player.setHealth(max);
        jpBar = new JProgressBar(min , max);
        //jpBar.setBackground(Color.blue);
        add(jpBar);
        jpBar.setValue(player.getHealth());
        jpBar.setAlignmentY(CENTER_ALIGNMENT);

        jpBar.setStringPainted(true);
        jpBar.setForeground(new Color(0 , 128 , 0));
        jpBar.setBorderPainted(true);
        jpBar.setBorder(BorderFactory.createLineBorder(Color.BLACK , 3));
    }

    public void changeValue(int difference)
    {
        player.setHealth(player.getHealth() + difference);
        //if(health>=100) health = 100;
        jpBar.setValue(player.getHealth());
    }

    public int getHealth() {
        return player.getHealth();
    }

    public void updateHealthBar()
    {
        jpBar.setValue( player.getHealth() );
    }
}
