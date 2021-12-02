package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.Constants.PPM;
import static com.mygdx.game.Constants.SCALE;


public class Application extends ApplicationAdapter {

	private boolean DEBUG = false;

	private OrthographicCamera orthographicCamera;

	private OrthogonalTiledMapRenderer tmr;
	private TiledMap map;

	private Box2DDebugRenderer b2dr;
	private World world;
	private Body player, platform;

	private SpriteBatch batch;
	private Texture texture;

	@Override
	public void create () {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		orthographicCamera = new OrthographicCamera();
		orthographicCamera.setToOrtho(false, width / SCALE,height / SCALE);

		world = new World(new Vector2(0,0), false); // default-gravity: -9.8f
		b2dr = new Box2DDebugRenderer();

		player = createBox(64,64,32,32,false);
		//platform = createBox(0,20,64,32,true);

		batch = new SpriteBatch();
		texture = new Texture("character_roundRed.png");

		map = new TmxMapLoader().load("map.tmx");
		tmr = new OrthogonalTiledMapRenderer(map);

		TiledObjectUtil.parseTiledObjectLayer(world,map.getLayers().get("collision").getObjects());
	}

	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());

		//Render
		Gdx.gl.glClearColor(0f,0f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(texture, player.getPosition().x * PPM - texture.getWidth() / 2,player.getPosition().y * PPM - texture.getHeight() / 2);
		batch.end();

		tmr.render();

		b2dr.render(world,orthographicCamera.combined.scl(PPM));
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void resize(int width, int height) {
		orthographicCamera.setToOrtho(false, width / SCALE, height / SCALE );
	}

	@Override
	public void dispose () {
		world.dispose();
		b2dr.dispose();
		batch.dispose();
		texture.dispose();
		tmr.dispose();
		map.dispose();
	}

	public void update(float deltaTime) {
		world.step(1 / 60f,6,2);

		inputUpdate(deltaTime);
		cameraUpdate(deltaTime);
		tmr.setView(orthographicCamera);
		batch.setProjectionMatrix(orthographicCamera.combined);
	}

	public void inputUpdate(float deltaTime) {
		int horizontalForce = 0;
		int verticalForce = 0;

		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			horizontalForce -= 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			horizontalForce += 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			verticalForce -= 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			verticalForce += 1;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			player.applyForceToCenter(0,300,false);
		}

		player.setLinearVelocity(horizontalForce * 5, verticalForce * 5);
	}

	public void cameraUpdate(float deltaTime) {
		Vector3 position = orthographicCamera.position;
		position.x = player.getPosition().x * PPM;
		position.y = player.getPosition().y * PPM;
		orthographicCamera.position.set(position);

		orthographicCamera.update();
	}

	public Body createBox(int xPosition, int yPosition, int width, int height, boolean isStatic) {
		Body playerBody;
		BodyDef bodyDef = new BodyDef();

		if(isStatic) {
			bodyDef.type = BodyDef.BodyType.StaticBody;
		} else {
			bodyDef.type = BodyDef.BodyType.DynamicBody;
		}
		bodyDef.position.set(xPosition / PPM, yPosition / PPM);
		bodyDef.fixedRotation = true;
		playerBody = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox((float)width / SCALE / PPM, (float)height / SCALE / PPM);

		playerBody.createFixture(shape, 1.0f);
		shape.dispose();
		return playerBody;
	}
}
