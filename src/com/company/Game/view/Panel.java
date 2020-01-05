package com.company.Game.view;

import com.company.Game.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Panel extends JPanel implements ActionListener{
    Image tree;
    Image brick;
    Image finishLineImg;
    private boolean disabled;

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    private static int [][] maze =
            {       {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
                    {8,0,0,0,6,0,1,0,0,0,0,0,1,0,0,0,1,1,1,8},
                    {8,0,1,1,2,0,0,0,1,1,1,0,0,0,1,0,0,0,0,8},
                    {8,0,1,0,1,1,1,8,0,0,0,1,1,0,1,1,1,1,0,8},
                    {8,0,1,0,0,0,0,0,1,1,1,0,1,0,1,1,1,1,0,8},
                    {8,8,8,0,1,1,1,0,1,0,0,2,1,0,1,1,1,1,2,8},
                    {8,6,1,0,1,4,2,0,1,0,1,0,1,7,1,1,1,1,0,8},
                    {8,0,1,0,1,1,1,0,1,0,1,0,1,8,1,1,1,1,6,8},
                    {8,0,0,0,5,0,0,0,4,0,1,0,1,1,1,1,0,0,6,8},
                    {8,1,1,0,1,1,1,1,1,1,1,0,0,0,1,1,0,1,1,8},
                    {8,1,0,0,1,1,1,1,1,1,1,1,1,5,1,1,0,1,1,8},
                    {8,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8},
                    {8,1,1,1,1,0,1,1,1,1,1,0,0,0,1,1,1,1,0,8},
                    {8,1,1,1,1,0,8,0,0,0,0,0,2,0,0,0,0,1,0,8},
                    {8,1,3,0,0,0,1,1,1,0,1,1,1,0,8,1,0,1,0,8},
                    {8,1,0,1,1,0,1,1,1,0,1,1,1,1,0,1,0,1,0,8},
                    {8,0,2,0,1,7,1,1,6,0,1,1,1,1,0,1,0,0,0,8},
                    {8,0,1,1,1,0,1,1,6,1,1,1,1,1,0,1,1,1,0,8},
                    {8,0,0,9,1,0,2,1,0,1,1,1,1,1,0,0,0,0,3,8},
                    {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
            };
    private static final int[][] defaultMaze = maze.clone();


    public static int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    Player player;
    Timer timer;

    public Panel() {
        player = Player.getInstance();
        setFocusable(true);

        brick = new ImageIcon("brick.jpg").getImage();

        InputMap im = getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RightArrow");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LeftArrow");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UpArrow");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DownArrow");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "Space");

        am.put("RightArrow", new MyKeyBindings("RightArrow"));
        am.put("LeftArrow", new MyKeyBindings("LeftArrow"));
        am.put("UpArrow", new MyKeyBindings("UpArrow"));
        am.put("DownArrow", new MyKeyBindings("DownArrow"));
        am.put("Space", new MyKeyBindings("Space"));

        timer = new Timer(25,this);
        timer.start();
        tree = new ImageIcon("tree image.png").getImage();
        try {
            BufferedImage img = ImageIO.read(new File("finish.png"));
            finishLineImg = MyImages.scale(img, BufferedImage.TYPE_INT_ARGB , 30 , 30 , 30.0/512, 30.0/512);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                switch ( GameMatrixEnum.getEquivalent(maze[row][col])) {
                    case PATH:
                        g.setColor(Color.WHITE);
                        g.fillRect(30 * col, 30 * row, 30, 30);
                        break;
                    case TREE:
                        g.drawImage(tree, 30 * col, 30 * row, 30, 30, this);
                        break;
                    case NORMAL_BOMB:
                        g.drawImage(NormalBomb.getBomb() ,30 * col, 30 * row, 30, 30, this);
                        break;
                    case NUCLEAR_BOMB:
                        g.drawImage(NuclearBomb.getBomb() ,30 * col, 30 * row, 30, 30, this);
                        break;
                    case HEALTH_GIFT:
                        g.drawImage(HealthGift.getGift() ,30 * col, 30 * row, 30, 30, this);
                        break;
                    case BULLET_GIFT:
                        g.drawImage(BulletGift.getBulletGift(),30 * col, 30 * row, 30, 30, this);
                        break;
                    case COIN_GIFT:
                        g.drawImage(CoinsGift.getCoin(),30 * col,30 * row, 30, 30, this);
                        break;
                    case ARMOR_GIFT:
                        g.drawImage(ArmorGift.getArmor(),30 * col, 30 * row , 30 , 30 , this);
                        break;
                    case BRICK:
                        g.drawImage(brick,30 * col, 30 * row , 30 , 30 , this);
                        break;
                    case TARGET://TARGET
                        g.drawImage(finishLineImg , 30 * col, 30 * row , this);
                        break;
                }
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);
            }
        }
        if( player.getPlayerState() instanceof NoArmor)
        {
            g.drawImage(player.getPickatchu() ,player.getxMaze()*30,player.getyMaze() * 30,this);
        }
        else {
            g.drawImage(player.getPickatchuArmor() ,player.getxMaze()*30,player.getyMaze() * 30,this);
        }
        //g.drawImage(player.getIcon(),player.getxMaze()*30,player.getyMaze() * 30,this);
    }

    public static void resetMaze()
    {
        for (int i = 0 ; i < maze.length ;++i) {
            maze[i] = defaultMaze[i].clone();
        }
    }
}
