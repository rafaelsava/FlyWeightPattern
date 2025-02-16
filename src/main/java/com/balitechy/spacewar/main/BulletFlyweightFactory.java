package com.balitechy.spacewar.main;

public class BulletFlyweightFactory {
    private static BulletFlyweight bulletFlyweight;

    public static BulletFlyweight getBulletFlyweight(Game game) {
        if (bulletFlyweight == null) {
            bulletFlyweight = new StandardBulletFlyweight(game);
        }
        return bulletFlyweight;
    }
}

