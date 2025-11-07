package com.Arkanoid.game.Factory;

import com.Arkanoid.game.Model.Bricks;
import com.Arkanoid.game.Utils.GameConfig;

public class BrickFactory {

    public static Bricks createFromMapData(int x, int y, int mapValue) {
        String type = String.valueOf(mapValue);

        if (mapValue == 9) {
            type = "unbreakable";
        }

        int hitPoint = getHitPointForType(mapValue);

        return new Bricks(x, y, hitPoint, GameConfig.BRICK_WIDTH, GameConfig.BRICK_HEIGHT, type);
    }

    private static int getHitPointForType(int mapValue) {
        switch (mapValue) {
            case 9: return Integer.MAX_VALUE; // Unbreakable
            case 0: return 0; // Empty
            default: return 1; // Normal brick
        }
    }
}
