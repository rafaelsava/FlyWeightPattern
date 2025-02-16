package com.balitechy.spacewar.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class EnemyController {
    // Se reemplazan las listas separadas por dos listas de GameEnemy para cada tipo.
    private LinkedList<GameEnemy> enemiesType1 = new LinkedList<>();
    private LinkedList<GameEnemy> enemiesType2 = new LinkedList<>();
    private Random random = new Random();
    private int spawnTimer = 0;
    private Game game;
    private final double SPAWN_PROBABILITY = 0.3; // Probabilidad para enemy2

    public EnemyController(Game game) {
        this.game = game;
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
        updateEnemies(enemiesType1);
        updateEnemies(enemiesType2);
    }

    // Método general para crear enemigos según el tipo
    private void spawnEnemy(String type) {
        int spawnX = random.nextInt(Game.WIDTH * Game.SCALE - (type.equals("enemy1") ? Enemy.WIDTH : Enemy2.WIDTH));
        int spawnY = - (type.equals("enemy1") ? Enemy.HEIGHT : Enemy2.HEIGHT);
        GameEnemy enemy = new GameEnemy(type, spawnX, spawnY, game);
        if (type.equals("enemy1")) {
            enemiesType1.add(enemy);
        } else {
            enemiesType2.add(enemy);
        }
    }

    private void updateEnemies(LinkedList<GameEnemy> list) {
        Iterator<GameEnemy> it = list.iterator();
        while (it.hasNext()) {
            GameEnemy enemy = it.next();
            enemy.tick();
            if (enemy.getY() > Game.HEIGHT * Game.SCALE) {
                it.remove();
            }
        }
    }

    public void render(Graphics g) {
        for (GameEnemy enemy : enemiesType1) {
            enemy.render(g);
        }
        for (GameEnemy enemy : enemiesType2) {
            enemy.render(g);
        }
    }

    // Métodos de acceso para colisiones (retornan copias de las listas)
    public ArrayList<GameEnemy> getEnemiesType1Copy() {
        return new ArrayList<>(enemiesType1);
    }

    public ArrayList<GameEnemy> getEnemiesType2Copy() {
        return new ArrayList<>(enemiesType2);
    }
}
