package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;

import java.util.Random;

import static com.mygdx.game.Constants.TILE_SIZE;
import static com.mygdx.game.Constants.WORLD_SIZE;

public class LevelMap extends TiledMap {

    private int[][] map;
    private final Texture texture;

    public LevelMap() {
        texture = new Texture(Gdx.files.internal("brick.png"));
        map = new int[WORLD_SIZE][WORLD_SIZE];
        generateMap(map);
    }

    public void drawGround(SpriteBatch batch) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map.length; col++) {
                    float x = row * TILE_SIZE;
                    float y = col * TILE_SIZE;

                    if(map[row][col] == 1) {
                        batch.draw(texture,x,y,TILE_SIZE,TILE_SIZE);
                    } else {

                    }
            }
        }
    }

    private void generateMap(int[][] map) {
        Random random = new Random();

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map.length; col++) {
                int num = random.nextInt(10);

                if(num > 7) {
                    map[row][col] = 0;
                } else {
                    map[row][col] = 1;
                }
            }
        }
    }
}
