package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import java.util.Random;

import static com.mygdx.game.utils.Constants.TILE_SIZE;
import static com.mygdx.game.utils.Constants.WORLD_SIZE;

public class LevelMap extends TiledMap {

    private int[][] map;
    private Tile tile;

    public LevelMap() {
        map = new int[WORLD_SIZE][WORLD_SIZE];
        tile = new Tile();
        generateMap(map);
    }

    private void generateMap(int[][] map) {
        Random random = new Random();

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map.length; col++) {
                int num = random.nextInt(10);

                if (row == 0 || row == map.length - 1 || col == 0 || col == map.length - 1) {
                    map[row][col] = 0;
                } else if (num > 7) {
                    map[row][col] = 1;
                } else if(num == 5) {
                    map[row][col] = 2;
                }else {
                    map[row][col] = 3;
                }
            }
        }
    }

    public void drawGround(SpriteBatch batch) {
        for (int row = map.length - 1; row >= 0; row--) {
            for (int col = map.length - 1; col >= 0; col--) {
                int id = map[row][col];
                float x = (col - row) * (TILE_SIZE / 2f);
                float y = (col + row) * (TILE_SIZE / 4f);
                    batch.draw(tile.getTileTexture(id),x,y,TILE_SIZE,TILE_SIZE);
            }
        }
    }
}
