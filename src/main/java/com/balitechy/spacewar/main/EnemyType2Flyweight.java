package com.balitechy.spacewar.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class EnemyType2Flyweight implements EnemyFlyweight {
    private BufferedImage image;

    // Se carga la imagen compartida para el enemigo tipo 2
    public EnemyType2Flyweight(Game game) {
        image = game.getSprites().getImage(18, 290, Enemy2.WIDTH, Enemy2.HEIGHT);
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(image, x, y, null);
    }
}
