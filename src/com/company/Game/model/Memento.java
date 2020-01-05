package com.company.Game.model;

import com.company.Game.view.Panel;

public class Memento {
    private int x;
    private int y;
    private int score;
    private int numOfBullets;
    private int health;
    private int timeLeft;
    private PlayerState playerState;
    private int[][] maze = new int[20][20];
    Panel panel = new Panel();


    public Memento(int health, int x, int y, int score, int numOfBullets, PlayerState playerState,int timeLeft) {
        this.health = health;
        this.x = x;
        this.y = y;
        this.score = score;
        this.numOfBullets = numOfBullets;
        this.playerState = playerState;
        this.timeLeft = timeLeft;
        this.maze = panel.getMaze();
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public int getHealth() {
        return health;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScore() {
        return score;
    }

    public int getNumOfBullets() {
        return numOfBullets;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    @Override
    public String toString() {
        return
                 x +
                "," + y +
                "," + score +
                "," + numOfBullets +
                "," + health +
                "," + timeLeft;
    }
}
