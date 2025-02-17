package com.balitechy.spacewar.main.Enemies;

import java.awt.Graphics;

public interface EnemyFlyweight {
    void render(Graphics g, int x, int y);
    int getWidth(); // Nuevos métodos
    int getHeight();
}