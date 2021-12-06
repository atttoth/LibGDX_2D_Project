package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.mygdx.game.Tiles.Brick;

public class Tile extends StaticTiledMapTile {

    public static final Tile brick = new Brick();

    public Tile(TextureRegion textureRegion) {
        super(textureRegion);
    }

}
