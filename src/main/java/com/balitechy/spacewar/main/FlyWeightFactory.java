package com.balitechy.spacewar.main;

import java.util.HashMap;
import java.util.Map;

public class FlyWeightFactory {
    private static final Map<String, EnemyFlyweight> pool = new HashMap<>();
    public static EnemyFlyweight getEnemyFlyweight(String type, Game game) {
        String key = type.toLowerCase();
        if (!pool.containsKey(key)) {
            if (key.equals("enemy1")) {
                pool.put(key, new EnemyType1Flyweight(game));
            } else if (key.equals("enemy2")) {
                pool.put(key, new EnemyType2Flyweight(game));
            } else {
                throw new IllegalArgumentException("Tipo de enemigo desconocido: " + type);
            }
        }
        return pool.get(key);
    }
}
