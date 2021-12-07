package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.mygdx.game.Tiles.Brick;
import com.mygdx.game.Tiles.Grass;

public class Tile extends StaticTiledMapTile {

    public static final Tile brick = new Brick();
    public static final Tile grass = new Grass();

    public Tile(TextureRegion textureRegion) {
        super(textureRegion);
    }

}
