package com.company.Game.model;

import java.awt.image.BufferedImage;

public class BulletGift extends Gift {
    static BufferedImage bulletGift;

    public static BufferedImage getBulletGift() {
        return bulletGift;
    }

    public static void setBulletGift(BufferedImage bulletGift) {
        BulletGift.bulletGift = bulletGift;
    }

    @Override
    public void getGiftImage() {

    }
}
