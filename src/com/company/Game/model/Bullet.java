package com.company.Game.model;

import java.awt.image.BufferedImage;

public class Bullet {
    private int x;
    private int y;
    static BufferedImage bullet;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static BufferedImage getBullet() {
        return bullet;
    }

    public static void setBullet(BufferedImage bullet) {
        Bullet.bullet = bullet;
    }
}
