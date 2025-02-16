package com.balitechy.spacewar.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class StandardBulletFlyweight implements BulletFlyweight {
    private BufferedImage image;
    public static final int WIDTH = 11;
    public static final int HEIGHT = 21;
    public static final int SPEED = 5;

    public StandardBulletFlyweight(Game game) {
        image = game.getSprites().getImage(35, 52, WIDTH, HEIGHT);
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(image, x, y, null);
    }
}

