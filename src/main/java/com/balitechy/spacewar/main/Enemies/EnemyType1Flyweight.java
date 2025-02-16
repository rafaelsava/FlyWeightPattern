package com.balitechy.spacewar.main.Enemies;

import com.balitechy.spacewar.main.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class EnemyType1Flyweight implements EnemyFlyweight {
    private BufferedImage image;
    private static final int WIDTH = 47;
    private final int HEIGHT = 41;


    public EnemyType1Flyweight(Game game) {
        image = game.getSprites().getImage(292, 15, WIDTH, HEIGHT);
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(image, x, y, null);
    }
    @Override
    public int getWidth() { return WIDTH; }
    @Override
    public int getHeight() { return HEIGHT; }

}


