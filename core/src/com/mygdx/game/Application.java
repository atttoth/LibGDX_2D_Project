package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Application extends ApplicationAdapter {

	private OrthographicCamera orthographicCamera;
	
	@Override
	public void create () {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		orthographicCamera = new OrthographicCamera();
		orthographicCamera.setToOrtho(false, width / 2,height / 2);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0f,0f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {
		orthographicCamera.setToOrtho(false, width / 2, height / 2 );
	}

	@Override
	public void dispose () {

	}
}
