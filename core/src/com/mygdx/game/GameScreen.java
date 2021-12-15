package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import static com.mygdx.game.utils.Constants.*;


public class GameScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private OrthographicCamera orthographicCamera;
    private LevelMap levelMap;
    private Player player;

    public GameScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void show() {
        orthographicCamera = new OrthographicCamera(SCREEN_WIDTH,SCREEN_HEIGHT);
        orthographicCamera.position.set(SCREEN_WIDTH / SCALE - (SCREEN_WIDTH / 2), SCREEN_HEIGHT / SCALE - (SCREEN_HEIGHT / 3), 10);

        player = new Player();

        batch = new SpriteBatch();

        levelMap = new LevelMap();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(orthographicCamera.combined);
        player.update();
        orthographicCamera.update();

        batch.begin();
        levelMap.drawGround(batch);
        player.render(batch);
        batch.end();
    }
}
