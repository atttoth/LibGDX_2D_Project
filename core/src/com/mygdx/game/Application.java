package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.Constants.PPM;


public class Application extends ApplicationAdapter {

	private OrthographicCamera orthographicCamera;

	private Box2DDebugRenderer b2dr;
	private World world;
	private Body player, platform;
	
	@Override
	public void create () {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		orthographicCamera = new OrthographicCamera();
		orthographicCamera.setToOrtho(false, width / 2,height / 2);

		world = new World(new Vector2(0,-9.8f), false);
		b2dr = new Box2DDebugRenderer();

		player = createBox(8,10,32,32,false);
		platform = createBox(0,0,64,32,true);
	}

	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());

		//Render
		Gdx.gl.glClearColor(0f,0f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		b2dr.render(world,orthographicCamera.combined.scl(PPM));
	}

	@Override
	public void resize(int width, int height) {
		orthographicCamera.setToOrtho(false, width / 2, height / 2 );
	}

	@Override
	public void dispose () {
		world.dispose();
		b2dr.dispose();
	}

	public void update(float deltaTime) {
		world.step(1 / 60f,6,2);

		inputUpdate(deltaTime);
		cameraUpdate(deltaTime);
	}

	public void inputUpdate(float deltaTime) {
		int horizontalForce = 0;
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			horizontalForce -= 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			horizontalForce += 1;
		}

		player.setLinearVelocity(horizontalForce * 5, player.getLinearVelocity().y);
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
		shape.setAsBox((float)width / 2 / PPM, (float)height / 2 / PPM);

		playerBody.createFixture(shape, 1.0f);
		shape.dispose();
		return playerBody;
	}
}
