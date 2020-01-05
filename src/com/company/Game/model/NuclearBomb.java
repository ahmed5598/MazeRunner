package com.company.Game.model;

import java.awt.image.BufferedImage;

/**
 * Created by FAST on 06-May-18.
 */
public class NuclearBomb {

    static BufferedImage bomb;
    final static private int damage = 50;

    public NuclearBomb() {
    }

    public static BufferedImage getBomb() {
        return bomb;
    }

    public static void setBomb(BufferedImage bomb) {
        NuclearBomb.bomb = bomb;
    }

    public static int getDamage() {
        return damage;
    }
}
