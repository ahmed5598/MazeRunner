package com.company.Game.model;

public class Originator {
    private int health;
    private int x;
    private int y;
    private int score;
    private int numOfBullets;
    private PlayerState playerState;
    private int timeLeft;
    private int[][] maze = new int[20][20];

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public void setNumOfBullets(int numOfBullets) {
        this.numOfBullets = numOfBullets;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public Memento storeMemento(){
        return new Memento(health,x,y,score,numOfBullets,playerState,timeLeft);
    }

    @Override
    public String toString() {
        return
                health +
                "," + x +
                ","  + y +
                "," + score +
                "," + numOfBullets;
    }
}
