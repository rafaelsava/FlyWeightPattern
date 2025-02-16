package com.balitechy.spacewar.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class EnemyType1Flyweight implements EnemyFlyweight {
    private BufferedImage image;


    public EnemyType1Flyweight(Game game) {
        image = game.getSprites().getImage(292, 15, Enemy.WIDTH, Enemy.HEIGHT);
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(image, x, y, null);
    }
}

