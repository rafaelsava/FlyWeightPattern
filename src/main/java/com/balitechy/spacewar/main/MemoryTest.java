package com.balitechy.spacewar.main;

public class MemoryTest {
    public static void main(String[] args) {
        // Forzar recolección de basura y medir la memoria inicial
        System.gc();
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memoria usada antes: " + memoryBefore + " bytes");

        // Ejecutar la funcionalidad a testear
        Game game = new Game();
        game.init();

        // Por ejemplo, simular la creación de muchos enemigos y balas
        // (Esto dependerá de la lógica de tu juego)
        for (int i = 0; i < 1000; i++) {
            // Agregar balas
            game.getBullets().addBullet(new GameBullet(100, 100, game));
            // Agregar enemigos de tipo 1
            game.getEnemies().getEnemiesType1().add(new GameEnemy("enemy1", 50, -Enemy.HEIGHT, game));
            // Agregar enemigos de tipo 2
            game.getEnemies().getEnemiesType2().add(new GameEnemy("enemy2", 150, -Enemy2.HEIGHT, game));
        }

        // Forzar recolección de basura y medir la memoria final
        System.gc();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memoria usada después: " + memoryAfter + " bytes");
        System.out.println("Diferencia (bytes): " + (memoryAfter - memoryBefore));
    }
}
