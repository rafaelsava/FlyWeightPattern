package com.balitechy.spacewar.main.Bullets;

import com.balitechy.spacewar.main.Game;

public class BulletFlyweightFactory {
    private static BulletFlyweight bulletFlyweight;

    public static BulletFlyweight getBulletFlyweight(Game game) {
        if (bulletFlyweight == null) {
            bulletFlyweight = new StandardBulletFlyweight(game);
        }
        return bulletFlyweight;
    }
}

