package com.balitechy.spacewar.main.Enemies;

import com.balitechy.spacewar.main.Game;
import java.awt.Graphics;
import java.util.*;

public class EnemyController {
    private LinkedList<GameEnemy> enemies = new LinkedList<>();
    private Random random = new Random();
    private int spawnTimer = 0;
    private Game game;
    private final double SPAWN_PROBABILITY = 0.3;

    public EnemyController(Game game) {
        this.game = game;
    }
    public ArrayList<GameEnemy> getEnemiesCopy() {
        return new ArrayList<>(enemies);
    }
    public void tick() {
        spawnTimer++;
        if (spawnTimer >= 60) {
            if (random.nextDouble() < SPAWN_PROBABILITY) {
                spawnEnemy("enemy2");
            } else {
                spawnEnemy("enemy1");
            }
            spawnTimer = 0;
        }
        updateEnemies();
    }

    private void spawnEnemy(String type) {
        EnemyFlyweight flyweight = EnemyFlyWeightFactory.getEnemyFlyweight(type, game);
        int width = flyweight.getWidth();
        int height = flyweight.getHeight();

        int spawnX = random.nextInt(Game.WIDTH * Game.SCALE - width);
        int spawnY = -height;

        GameEnemy enemy = new GameEnemy(type, spawnX, spawnY, game);
        enemies.add(enemy);
    }

    private void updateEnemies() {
        Iterator<GameEnemy> it = enemies.iterator();
        while (it.hasNext()) {
            GameEnemy enemy = it.next();
            enemy.tick();
            if (enemy.getY() > Game.HEIGHT * Game.SCALE) {
                it.remove();
            }
        }
    }

    public void render(Graphics g) {
        for (GameEnemy enemy : enemies) {
            enemy.render(g);
        }
    }



    public void removeEnemies(ArrayList<GameEnemy> enemiesToRemove) {
        enemies.removeAll(enemiesToRemove);
    }
}