package com.balitechy.spacewar.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explosion {
    private double x;
    private double y;
    private int frame = 0;
    private boolean active = false;
    private BufferedImage[] frames = new BufferedImage[3];
    private Game game;

    private int animationDelay = 5; // Cambia de frame cada 5 ticks
    private int currentDelay = 0;

    public Explosion(Game game) {
        this.game = game;
        SpritesImageLoader sprites = game.getSprites();
        frames[0] = sprites.getImage(474, 15, 50, 50);
        frames[1] = sprites.getImage(468, 77, 70, 70);
        frames[2] = sprites.getImage(456, 159, 90, 90);
    }

    public void activate(double x, double y) {
        this.x = x - 22;
        this.y = y - 22;
        this.active = true;
        this.frame = 0;
    }

    public void tick() {
        if (active) {
            currentDelay++;
            if (currentDelay >= animationDelay) {
                if (frame < frames.length - 1) {
                    frame++;
                } else {
                    active = false;
                }
                currentDelay = 0;
            }
        }
    }

    public void render(Graphics g) {
        if(active) {
            g.drawImage(frames[frame], (int)x, (int)y, null);
        }
    }

    public boolean isActive() {
        return active;
    }
}