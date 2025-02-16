package com.balitechy.spacewar.main.Bullets;

import com.balitechy.spacewar.main.Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class GameBullet {
    private int x;
    private int y;
    private BulletFlyweight flyweight;

    public GameBullet(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.flyweight = BulletFlyweightFactory.getBulletFlyweight(game);
    }

    public void tick() {
        y -= StandardBulletFlyweight.SPEED;
    }

    public void render(Graphics g) {
        flyweight.render(g, x, y);
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, StandardBulletFlyweight.WIDTH, StandardBulletFlyweight.HEIGHT);
    }
}

