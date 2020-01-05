package com.company.Game.model;

import java.awt.image.BufferedImage;

public class CoinsGift extends Gift {
    static BufferedImage coin;
    final static private int effect = 5;

    @Override
    public void getGiftImage() {

    }


    public static BufferedImage getCoin() {
        return coin;
    }

    public static void setCoinGift(BufferedImage coin) {
        CoinsGift.coin = coin;
    }

    public static int getEffect() {
        return effect;
    }
}
