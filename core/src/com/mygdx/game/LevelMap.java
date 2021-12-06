package com.mygdx.game;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import java.util.Random;

import static com.mygdx.game.utils.Constants.TILE_SIZE;
import static com.mygdx.game.utils.Constants.WORLD_SIZE;

public class LevelMap extends TiledMap {

    private int[][] map;
    private TiledMapTileLayer tileLayer;

    public LevelMap() {
        tileLayer = new TiledMapTileLayer(WORLD_SIZE,WORLD_SIZE,TILE_SIZE,TILE_SIZE);
        map = new int[WORLD_SIZE][WORLD_SIZE];
        generateMap(map);
    }

    private void generateMap(int[][] map) {
        MapLayers mapLayers = getLayers();
        Random random = new Random();

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map.length; col++) {
                int num = random.nextInt(10);
                Cell cell = new Cell();

                if(num < 7) {
                    cell.setTile(Tile.brick);
                    tileLayer.setCell(row,col,cell);
                } else {
                }
            }
        }
        mapLayers.add(tileLayer);
    }
}
