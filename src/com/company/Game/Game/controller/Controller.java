package com.company.Game.Game.controller;

import com.company.Game.model.*;
import com.company.Game.view.EndDialog;
import com.company.Game.view.GameMatrixEnum;
import com.company.Game.view.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class Controller {
    final Player player = Player.getInstance();
    PlayerInterface player1 =  Player.getInstance();
    final HealthBar healthBar = HealthBar.getInstance();
    static final private Controller controller = new Controller();
    private View view;
    private Updates updates = new Updates();
    private UpdatesObserver observer = new UpdatesObserver();
    public StopWatch stopWatch;


    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public static Controller getInstance() {
        return controller;
    }

    private Controller(){
    }

    public void fireBullet(){
        int[][] mazeMatrix = view.getGamePanel().getMaze();
        int x = player.getxMaze();
        int y = player.getyMaze();
        Color color = null;
        switch (player.getDirection()){
            case "right":
                while(mazeMatrix[y][x] == 0 || mazeMatrix[y][x] == 9) {

                    x++;
                    view.getGamePanel().getGraphics().drawImage(Bullet.getBullet(),30*x,30*y,30,30,null);
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(mazeMatrix[y][x] == 8){
                        player.shootBullet();
                        view.updateNumberOfBulletsLbl();
                        break;
                    }
                }
                if (mazeMatrix[y][x] != 8) {
                    try{
                    BufferedImage img = ImageIO.read(new File("explosion.gif"));
                    img = scale(img , BufferedImage.TYPE_INT_ARGB , 30 , 30 , 30.0/450 , 30.0/274);
                    view.getGamePanel().getGraphics().drawImage(img,30*x,30*y,30,30,null);
                        try {
                            Thread.sleep(40);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    mazeMatrix[y][x] = 0;
                    player.shootBullet();
                    view.updateNumberOfBulletsLbl();}catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case "left":
                while(mazeMatrix[y][x] == 0 || mazeMatrix[y][x] == 9) {

                    x--;
                    view.getGamePanel().getGraphics().drawImage(Bullet.getBullet(), 30 * x, 30 * y, 30, 30, null);
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(mazeMatrix[y][x] == 8){
                        player.shootBullet();
                        view.updateNumberOfBulletsLbl();
                        break;
                    }
                }
                if(mazeMatrix[y][x] != 8) {
                    try{
                        BufferedImage img = ImageIO.read(new File("explosion.gif"));
                        img = scale(img , BufferedImage.TYPE_INT_ARGB , 30 , 30 , 30.0/450 , 30.0/274);
                        view.getGamePanel().getGraphics().drawImage(img,30*x,30*y,30,30,null);
                        try {
                            Thread.sleep(40);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mazeMatrix[y][x] = 0;
                        player.shootBullet();
                        view.updateNumberOfBulletsLbl();}catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case "up":
                while(mazeMatrix[y][x] == 0 || mazeMatrix[y][x] == 9 ) {

                    y--;
                    view.getGamePanel().getGraphics().drawImage(Bullet.getBullet(), 30 * x, 30 * y, 30, 30, null);
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(mazeMatrix[y][x] == 8){
                        player.shootBullet();
                        view.updateNumberOfBulletsLbl();
                        break;
                    }
                }

                if(mazeMatrix[y][x] != 8) {
                    try{
                        BufferedImage img = ImageIO.read(new File("explosion.gif"));
                        img = scale(img , BufferedImage.TYPE_INT_ARGB , 30 , 30 , 30.0/450 , 30.0/274);
                        view.getGamePanel().getGraphics().drawImage(img,30*x,30*y,30,30,null);
                        try {
                            Thread.sleep(40);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mazeMatrix[y][x] = 0;
                        player.shootBullet();
                        view.updateNumberOfBulletsLbl();}catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case "down":
                while(mazeMatrix[y][x] == 0 || mazeMatrix[y][x] == 9 ) {

                    y++;
                    //System.out.println(view.getGamePanel());
                    view.getGamePanel().getGraphics().drawImage(Bullet.getBullet(), 30 * x, 30 * y, 30, 30, null);
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (mazeMatrix[y][x] == 8) {
                        player.shootBullet();
                        view.updateNumberOfBulletsLbl();
                        break;
                    }

                }
               if ( mazeMatrix[y][x] != 8) {
                   try{
                       BufferedImage img = ImageIO.read(new File("explosion.gif"));
                       img = scale(img , BufferedImage.TYPE_INT_ARGB , 30 , 30 , 30.0/450 , 30.0/274);
                       view.getGamePanel().getGraphics().drawImage(img,30*x,30*y,30,30,null);
                       try {
                           Thread.sleep(40);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       mazeMatrix[y][x] = 0;
                       player.shootBullet();
                       view.updateNumberOfBulletsLbl();}catch (Exception e){
                       e.printStackTrace();
                   }
               }
                break;
        }
    }


    public void updateInfo()
    {
        int score = 0;
        int x = player.getxMaze();
        int y = player.getyMaze();
        updates.register(observer);
        PlayerState pp = null;

        int[][] mazeMatrix = view.getGamePanel().getMaze();

        switch (GameMatrixEnum.getEquivalent(mazeMatrix[y][x])) //y then x!!!!! don't change 7aga
        {
            case PATH:
                break;
            case TREE:
                //System.out.println("Tree");
                break;
            case NORMAL_BOMB:
                //System.out.println("Normal bomb");
                if(!player1.move())
                {
                    updates.setUpdates(-NormalBomb.getDamage(),0);
                    view.getGamePanel().repaint();
                    mazeMatrix[y][x] = 0;
                    if ( healthBar.getHealth() <= 0) {
                        view.getGamePanel().setDisabled(true);
                        new EndDialog(stopWatch , "Killed" , view).execute();
                        view.getGamePanel().setDisabled(false);
                    }
                }
                else mazeMatrix[y][x] = 0;

                break;
            case NUCLEAR_BOMB:
                //System.out.println("Nuclear bomb");
                if(!player1.move())
                {
                    updates.setUpdates(-NuclearBomb.getDamage(),0);
                    view.getGamePanel().repaint();
                    mazeMatrix[y][x] = 0;
                    if ( healthBar.getHealth() <= 0)
                    {
                        view.getGamePanel().setDisabled(true);
                        new EndDialog(stopWatch , "Killed" , view).execute();
                        view.getGamePanel().setDisabled(false);
                    }
                }
                else mazeMatrix[y][x] = 0;
                break;
            case HEALTH_GIFT:
                updates.setUpdates(HealthGift.getEffect(),0);
                view.getGamePanel().repaint();
                if ( healthBar.getHealth() >= 100)
                {
                    player.setHealth(100);
                }
                mazeMatrix[y][x] = 0;
                break;
            case BULLET_GIFT:
                player.setNumOfBullets(player.getNumOfBullets() + 2);
                view.updateNumberOfBulletsLbl();
                //System.out.println("2 bullets added");
                mazeMatrix[y][x] = 0;
                break;
            case COIN_GIFT:
                score = score + CoinsGift.getEffect();
                updates.setUpdates(0,score);
                view.editScore(score);
                view.getGamePanel().repaint();
                mazeMatrix[y][x] = 0;
                break;
            case ARMOR_GIFT:
                player1 = new Armor(Player.getInstance());
                view.getGamePanel().repaint();
                mazeMatrix[y][x] = 0;
                break;
            case TARGET://TARGET
                view.getGamePanel().setDisabled(true);
                new EndDialog(stopWatch , "YOU WIN!" , view).execute();
                view.getGamePanel().setDisabled(false);
                break;
        }
    }

    public boolean isValidMove(int changeInX , int changeInY)
    {
        int playerX = player.getxMaze() + changeInX;
        int playerY = player.getyMaze() + changeInY;

        if( playerX < 0 || playerY < 0)
            return false;

        if( playerX > 19 || playerY > 19)
            return false;
        int field = view.getGamePanel().getMaze()[playerY][playerX];
        if( GameMatrixEnum.getEquivalent(field) == GameMatrixEnum.TREE || GameMatrixEnum.getEquivalent(field) == GameMatrixEnum.BRICK)
            return false;

        return true;
    }
    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }
}
