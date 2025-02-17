package com.balitechy.spacewar.main.Enemies;

import com.balitechy.spacewar.main.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class EnemyType2Flyweight implements EnemyFlyweight {
    private BufferedImage image;
    public static final int WIDTH = 42;
    public static final int HEIGHT = 40;

    // Se carga la imagen compartida para el enemigo tipo 2
    public EnemyType2Flyweight(Game game) {
        image = game.getSprites().getImage(18, 290, WIDTH, HEIGHT);
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
