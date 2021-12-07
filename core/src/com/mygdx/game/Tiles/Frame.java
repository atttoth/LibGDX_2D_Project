package com.mygdx.game.Tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Tile;

public class Frame extends Tile {

    public Frame() {
        super(new TextureRegion(new Texture(Gdx.files.internal("frame.png"))));
    }
}
