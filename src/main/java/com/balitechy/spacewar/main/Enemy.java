package com.balitechy.spacewar.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy{
    private double x;
    private double y;
    private double velY;
    public static final int WIDTH = 47;
    public static final int HEIGHT = 41;
    private BufferedImage image;
    private Game game;

    public Enemy(double x, double y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.velY = 2; // Velocidad de caída
        image = game.getSprites().getImage(292, 15, WIDTH, HEIGHT); // Ajusta coordenadas según tu sprite
    }

    public void tick() {
        y += velY;
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

     public Rectangle getBounds() {
        return new Rectangle(
                (int)x,
                (int)y,
                WIDTH,
                HEIGHT
        );
    }

        public double getY() {
        return y;
    }
}