package com.balitechy.spacewar.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class EnemyController {
    private LinkedList<Enemy> enemiesType1 = new LinkedList<>();
    private LinkedList<Enemy2> enemiesType2 = new LinkedList<>();
    private Random random = new Random();
    private int spawnTimer = 0;
    private Game game;
    private final double SPAWN_PROBABILITY = 0.3; // 30% para Enemy2

    public EnemyController(Game game) {
        this.game = game;
    }

    public void tick() {
        spawnTimer++;

        // Generación de nuevos enemigos
        if (spawnTimer >= 60) {
            if(random.nextDouble() < SPAWN_PROBABILITY) {
                spawnEnemyType2();
            } else {
                spawnEnemyType1();
            }
            spawnTimer = 0;
        }

        // Actualizar ambos tipos de enemigos
        updateEnemiesType1();
        updateEnemiesType2();
    }

    private void spawnEnemyType1() {
        enemiesType1.add(new Enemy(
                random.nextInt(Game.WIDTH * Game.SCALE - Enemy.WIDTH),
                -Enemy.HEIGHT,
                game
        ));
    }

    private void spawnEnemyType2() {
        enemiesType2.add(new Enemy2(
                random.nextInt(Game.WIDTH * Game.SCALE - Enemy2.WIDTH),
                -Enemy2.HEIGHT,
                game
        ));
    }

    private void updateEnemiesType1() {
        Iterator<Enemy> it = enemiesType1.iterator();
        while(it.hasNext()) {
            Enemy e = it.next();
            e.tick();
            if(e.getY() > Game.HEIGHT * Game.SCALE) {
                it.remove();
            }
        }
    }

    private void updateEnemiesType2() {
        Iterator<Enemy2> it = enemiesType2.iterator();
        while(it.hasNext()) {
            Enemy2 e = it.next();
            e.tick();
            if(e.getY() > Game.HEIGHT * Game.SCALE) {
                it.remove();
            }
        }
    }

    public void render(Graphics g) {
        renderEnemiesType1(g);
        renderEnemiesType2(g);
    }

    private void renderEnemiesType1(Graphics g) {
        for(Enemy e : enemiesType1) {
            e.render(g);
        }
    }

    private void renderEnemiesType2(Graphics g) {
        for(Enemy2 e : enemiesType2) {
            e.render(g);
        }
    }

    // Métodos de acceso para colisiones
    public ArrayList<Enemy> getEnemiesType1Copy() {
        return new ArrayList<>(enemiesType1);
    }

    public ArrayList<Enemy2> getEnemiesType2Copy() {
        return new ArrayList<>(enemiesType2);
    }

    // Métodos para eliminar enemigos
    public void removeEnemyType1(Enemy enemy) {
        enemiesType1.remove(enemy);
    }

    public void removeEnemyType2(Enemy2 enemy) {
        enemiesType2.remove(enemy);
    }

    public LinkedList<Enemy> getEnemiesType1() {
        return enemiesType1;
    }

    public LinkedList<Enemy2> getEnemiesType2() {
        return enemiesType2;
    }
}