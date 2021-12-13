package com.mygdx.game.Tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Tile;

public class Stone extends Tile {

    public Stone() {
        super(new TextureRegion(new Texture(Gdx.files.internal("stone.png"))));
    }
}
