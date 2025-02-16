package com.balitechy.spacewar.main.Enemies;

import com.balitechy.spacewar.main.Game;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameEnemy {
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
        this.flyweight = EnemyFlyWeightFactory.getEnemyFlyweight(this.type, game);

        // Configurar parámetros de movimiento según el tipo
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
            x += Math.sin(angle) * 0.8;
            y += velY;
        }
    }

    public void render(Graphics g) {
        flyweight.render(g, x, y);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, flyweight.getWidth(), flyweight.getHeight());
    }

    public int getY() {
        return y;
    }
}