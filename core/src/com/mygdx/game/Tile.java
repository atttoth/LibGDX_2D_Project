package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.mygdx.game.Tiles.Stone;
import com.mygdx.game.Tiles.Frame;
import com.mygdx.game.Tiles.Grass;

public class Tile extends StaticTiledMapTile {

    public static final Tile brick = new Stone();
    public static final Tile grass = new Grass();
    public static final Tile frame = new Frame();

    public Tile(TextureRegion textureRegion) {
        super(textureRegion);
    }

}
