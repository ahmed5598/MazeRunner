package com.company.Game.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by FAST on 06-May-18.
 */
public class MyImages {


    /**
     * Code from stackoverflow el sara7a ya3ny
     * scale image
     *
     * @param sbi image to scale
     * @param imageType type of image
     * @param dWidth width of destination image
     * @param dHeight height of destination image
     * @param fWidth x-factor for transformation / scaling
     * @param fHeight y-factor for transformation / scaling
     * @return scaled image
     */
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

    public static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }


    public static void loadImages()
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("normalbomb.png"));
            img = scale(img , BufferedImage.TYPE_INT_ARGB , 30 , 30 , 30.0/880 , 30.0/720);
            NormalBomb.setBomb(img);

            img = ImageIO.read(new File("nuclearbomb.png"));
            img = scale(img , BufferedImage.TYPE_INT_ARGB , 30 , 30 , 0.0505 , 0.0505);
            NuclearBomb.setBomb(img);

            img = ImageIO.read(new File("healthgift.png"));
            img = scale(img , BufferedImage.TYPE_INT_ARGB , 30 , 30 , 30.0/1500 , 30.0/1400);
            HealthGift.setGift(img);

            img = ImageIO.read(new File("bulletGift.png"));
            img = scale(img , BufferedImage.TYPE_INT_ARGB , 30 , 30 , 30.0/800 , 30.0/622);
            BulletGift.setBulletGift(img);

            img = ImageIO.read(new File("alicoin.png"));
            img = scale(img , BufferedImage.TYPE_INT_ARGB , 30 , 30 , 30.0/256 , 30.0/256);
            CoinsGift.setCoinGift(img);

            img = ImageIO.read(new File("armorgift.png"));
            img = scale(img , BufferedImage.TYPE_INT_ARGB , 30 , 30 , 30.0/1600 , 30.0/1600);
            ArmorGift.setArmorGift(img);

            img = ImageIO.read(new File("Fireball.png"));
            img = scale(img , BufferedImage.TYPE_INT_ARGB , 30 , 30 , 30.0/940 , 30.0/600);
            Bullet.setBullet(img);

            Image image = new ImageIcon("ezgif.com-resize.gif").getImage();

            Player player = Player.getInstance();
            player.setIcon(image);

            img = ImageIO.read(new File("pikachu.png"));
            img = scale(img , BufferedImage.TYPE_INT_ARGB , 30 , 30 , 30.0/1000 , 30.0/1000);
            player.setPickatchu(img);

            img = ImageIO.read(new File("pikachu_armor.png"));
            img = scale(img , BufferedImage.TYPE_INT_ARGB , 30 , 30 , 30.0/600 , 30.0/720);
            player.setPickatchuArmor(img);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
