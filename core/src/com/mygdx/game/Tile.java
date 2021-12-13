package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Tile {
    private Texture frame;
    private Texture dirt;
    private Texture stone;
    private Texture grass;

    public Tile() {
        frame = new Texture(Gdx.files.internal("frame.png"));
        dirt = new Texture(Gdx.files.internal("dirt.png"));
        stone = new Texture(Gdx.files.internal("stone.png"));
        grass = new Texture(Gdx.files.internal("grass.png"));
    }

    public Texture getTileTexture(int id) {
        if (id == 0) {
            return frame;
        } else if (id == 1) {
            return dirt;
        } else if (id == 2) {
            return stone;
        } else {
            return grass;
        }
    }
}
