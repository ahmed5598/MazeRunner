package com.company.Game.model;

import java.awt.image.BufferedImage;

public class ArmorGift extends Gift {
    static BufferedImage armor;

    @Override
    public void getGiftImage() {

    }

    public static BufferedImage getArmor() {
        return armor;
    }

    public static void setArmorGift(BufferedImage armor) {
        ArmorGift.armor = armor;
    }
}
