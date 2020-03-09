package com.codecool.lifeOfAnts.view;

import com.codecool.lifeOfAnts.model.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 8;

    private static Image tileset = new Image("/tiles6.png", 271, 271, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        // todo w = width, h = height, x,y = x_coord, y_coord
        final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH );
            y = j * (TILE_WIDTH );
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("Queen", new Tile(5, 0));
        tileMap.put("Soldier", new Tile(2, 0));
        tileMap.put("Drone", new Tile(3, 0));
        tileMap.put("Worker", new Tile(1, 0));
        tileMap.put("Wasp", new Tile(4, 0));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
