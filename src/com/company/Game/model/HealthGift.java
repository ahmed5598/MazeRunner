package com.company.Game.model;

import java.awt.image.BufferedImage;

public class HealthGift extends Gift {

    static BufferedImage gift;
    final static private int effect = 30;

    public static BufferedImage getGift() {
        return gift;
    }

    public static void setGift(BufferedImage gift) {
        HealthGift.gift = gift;
    }

    public static int getEffect() {
        return effect;
    }

    @Override
    public void getGiftImage() {

    }
}
