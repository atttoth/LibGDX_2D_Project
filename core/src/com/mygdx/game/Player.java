package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Player implements Entity {

    private Texture image;
    private Vector2 position;

    public Player() {
        image = new Texture(Gdx.files.internal("character_roundRed.png"));
        position = new Vector2(0,0);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, position.x,position.y);
    }

    @Override
    public void update() {
        playerMovement();
    }

    private void playerMovement() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            position.x -= 16;
            position.y += 8;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            position.x += 16;
            position.y -= 8;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            position.x -= 16;
            position.y -= 8;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            position.x += 16;
            position.y += 8;
        }
    }
}
