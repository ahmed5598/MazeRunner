package com.company.Game.model;

import com.company.Game.Game.controller.StopWatch;

import java.awt.*;

public class Player implements PlayerInterface {
    private int score;
    private int xMaze, yMaze;
    private Bullet bullet;
    private int numOfBullets;
    public Image icon;
    private Image pickatchu;
    private Image pickatchuArmor;
    private int health;
    private String direction;
    private static final Player firstInstance = new Player();

    PlayerState hasArmor;
    PlayerState noArmor;
    PlayerState playerState;

    private Player() {
        score = 0;
        xMaze = 1;
        yMaze = 1;
        numOfBullets = 6;
        direction = "down";
        hasArmor = new HasArmor(this);
        noArmor = new NoArmor(this);
        playerState = noArmor;

    }

    public void shootBullet(){
        if(numOfBullets > 0)
        {
            numOfBullets--;
        }

    }

    public void setMemento(Memento memento)
    {
        this.numOfBullets = memento.getNumOfBullets();
        this.xMaze = memento.getX();
        this.yMaze = memento.getY();
        this.score = memento.getScore();
        this.health = memento.getHealth();
        this.playerState = memento.getPlayerState();
    }
    public Memento getMemento()
    {
        return new Memento(health,xMaze,yMaze,score,numOfBullets,playerState, StopWatch.getTimeLeft());
    }


    public Image getIcon() {
        return icon;
    }

    public void updateScore(int value){
        this.score+=value;
    }

    public void movePlayer( int xMaze, int yMaze){
        this.xMaze += xMaze;
        this.yMaze += yMaze;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getxMaze() {
        return xMaze;
    }

    public void setxMaze(int xMaze) {
        this.xMaze = xMaze;
    }

    public int getyMaze() {
        return yMaze;
    }

    public void setyMaze(int yMaze) {
        this.yMaze = yMaze;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public static Player getInstance(){
        return firstInstance;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getNumOfBullets() {
        return numOfBullets;
    }

    public void setNumOfBullets(int numOfBullets) {
        this.numOfBullets = numOfBullets;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setPlayerState(PlayerState newPlayerState)
    {
        playerState = newPlayerState;

    }
    public boolean move()
    {
        return playerState.move();

    }

    public void resetAttributes()
    {
        health = 100;
        score = 0;
        xMaze = 1;
        yMaze = 1;
        numOfBullets = 6;
        direction = "down";
        playerState = noArmor;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public PlayerState getHasArmor()
    {
        return hasArmor;
    }
    public PlayerState getNoArmor()
    {
        return noArmor;
    }

    public Image getPickatchu() {
        return pickatchu;
    }

    public void setPickatchu(Image pickatchu) {
        this.pickatchu = pickatchu;
    }

    public Image getPickatchuArmor() {
        return pickatchuArmor;
    }

    public void setPickatchuArmor(Image pickatchuArmor) {
        this.pickatchuArmor = pickatchuArmor;
    }
}