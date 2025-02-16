package com.balitechy.spacewar.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy2 {
    private double x;
    private double y;
    private double angle = 0;
    private BufferedImage image;
    private Game game;

    public static final int WIDTH = 42;
    public static final int HEIGHT = 40;

    public Enemy2(double x, double y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;

        // Cargar sprite diferente (coordenadas según tu imagen)
        this.image = game.getSprites().getImage(18, 290, WIDTH, HEIGHT);
    }

    public void tick() {
        // Movimiento sinusoidal
        angle += 0.1;
        x += Math.sin(angle) * 0.8; // Movimiento lateral
        y += 2; // Velocidad de caída

        // Limites de pantalla
        if(x < 0) x = 0;
        if(x > Game.WIDTH * Game.SCALE - WIDTH) x = Game.WIDTH * Game.SCALE - WIDTH;
    }

    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
    }

    // Getters para posición
    public double getX() { return x; }
    public double getY() { return y; }
}