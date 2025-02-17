package com.balitechy.spacewar.main;

import java.util.LinkedList;
import java.util.Random;

public class MemoryTest{

    public static void main(String[] args) {
        // Crear una instancia de Game para disponer de recursos (por ejemplo, sprites)
        Game game = new Game();
        game.init();  // Inicializa todos los componentes y carga las imágenes

        // Forzar recolección de basura para obtener una medición estable
        forceGC();
        long memoryBefore = getUsedMemory();
        System.out.println("Memoria usada ANTES de crear objetos: " + memoryBefore + " bytes");

        // Número de instancias por cada tipo de enemigo y balas
        int countEnemies = 10000;
        int countBullets = 10000;
        LinkedList<GameEnemy> enemyList1 = new LinkedList<>();
        LinkedList<GameEnemy> enemyList2 = new LinkedList<>();
        LinkedList<GameBullet> bulletList = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < countEnemies; i++) {
            enemyList1.add(new GameEnemy("enemy1",
                    random.nextInt(Game.WIDTH * Game.SCALE - Enemy.WIDTH),
                    -Enemy.HEIGHT,
                    game
            ));
            enemyList2.add(new GameEnemy("enemy2",
                    random.nextInt(Game.WIDTH * Game.SCALE - Enemy2.WIDTH),
                    -Enemy2.HEIGHT,
                    game
            ));
        }

        // Crear instancias de GameBullet (balas)
        for (int i = 0; i < countBullets; i++) {
            bulletList.add(new GameBullet(
                    random.nextInt(Game.WIDTH * Game.SCALE - StandardBulletFlyweight.WIDTH),
                    random.nextInt(Game.HEIGHT * Game.SCALE - StandardBulletFlyweight.HEIGHT),
                    game
            ));
        }

        // Forzar GC nuevamente para estabilizar la medición
        forceGC();
        long memoryAfter = getUsedMemory();
        int totalObjects = (countEnemies * 2) + countBullets;
        System.out.println("Memoria usada DESPUÉS de crear " + totalObjects + " objetos: " + memoryAfter + " bytes");
        System.out.println("Incremento en memoria: " + (memoryAfter - memoryBefore) + " bytes");
    }

    // Método auxiliar para obtener la memoria utilizada
    private static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    // Método para forzar el GC y pausar brevemente
    private static void forceGC() {
        System.gc();
        try {
            Thread.sleep(1000); // Espera 1 segundo para dar tiempo al GC
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}