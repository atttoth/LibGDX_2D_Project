package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.utils.Constants.*;


public class GameScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private OrthographicCamera orthographicCamera;
    private LevelMap levelMap;

    private Box2DDebugRenderer b2dr;
    private World world;

    private Body player;
    private Texture texture;

    private OrthogonalTiledMapRenderer renderer;

    public GameScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void show() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false,width / SCALE, height / SCALE);

        world = new World(new Vector2(0,0), false); // default-gravity: -9.8f
        b2dr = new Box2DDebugRenderer();

        player = createBox((WORLD_SIZE * TILE_SIZE) / 2,(WORLD_SIZE * TILE_SIZE) / 2,32,32,false);

        batch = new SpriteBatch();
        texture = new Texture("character_roundRed.png");

        levelMap = new LevelMap();
        renderer = new OrthogonalTiledMapRenderer(levelMap);
    }

    @Override
    public void render(float delta) {
        update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        batch.begin();
        batch.draw(texture, player.getPosition().x * TILE_SIZE - (float)texture.getWidth() / 2,player.getPosition().y * TILE_SIZE - (float)texture.getHeight() / 2);
        batch.end();

        b2dr.render(world,orthographicCamera.combined.scl(TILE_SIZE));
    }

	@Override
	public void resize(int width, int height) {
        orthographicCamera.setToOrtho(false, width / SCALE, height / SCALE );
	}

	@Override
    public void dispose() {
        batch.dispose();
    }

    public void update(float deltaTime) {
        world.step(1 / 60f,6,2);

        inputUpdate(deltaTime);
        cameraUpdate(deltaTime);
        renderer.setView(orthographicCamera);
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
      /*  if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.applyForceToCenter(0,300,false);
        } */

        player.setLinearVelocity(horizontalForce * 5, verticalForce * 5);
    }

    public void cameraUpdate(float deltaTime) {
        Vector3 position = orthographicCamera.position;
        position.x = player.getPosition().x * TILE_SIZE;
        position.y = player.getPosition().y * TILE_SIZE;
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
        bodyDef.position.set((float)xPosition / TILE_SIZE, (float)yPosition / TILE_SIZE);
        bodyDef.fixedRotation = true;
        playerBody = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((float)width / SCALE / TILE_SIZE, (float)height / SCALE / TILE_SIZE);

        playerBody.createFixture(shape, 1.0f);
        shape.dispose();
        return playerBody;
    }
}
