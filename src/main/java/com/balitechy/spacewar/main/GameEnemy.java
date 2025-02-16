package com.balitechy.spacewar.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class GameEnemy {
    // Estado extrínseco
    private int x;
    private int y;
    private double velY;
    private double angle;
    private String type;

    private EnemyFlyweight flyweight;

    public GameEnemy(String type, int x, int y, Game game) {
        this.type = type.toLowerCase();
        this.x = x;
        this.y = y;
        this.flyweight = FlyWeightFactory.getEnemyFlyweight(this.type, game);
        if (this.type.equals("enemy1")) {
            this.velY = 2;
        } else if (this.type.equals("enemy2")) {
            this.velY = 2;
            this.angle = 0;
        }
    }

    public void tick() {
        if (type.equals("enemy1")) {
            y += velY;
        } else if (type.equals("enemy2")) {
            angle += 0.1;
            x += Math.sin(angle) * 0.8; // Movimiento lateral
            y += velY;
        }
    }

    public void render(Graphics g) {
        flyweight.render(g, x, y);
    }

    public Rectangle getBounds() {
        if (type.equals("enemy1")) {
            return new Rectangle(x, y, Enemy.WIDTH, Enemy.HEIGHT);
        } else { // enemy2
            return new Rectangle(x, y, Enemy2.WIDTH, Enemy2.HEIGHT);
        }
    }

    public int getY() {
        return y;
    }
}

