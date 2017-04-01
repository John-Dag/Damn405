package com.nightglyphstudio.damn405;

import box2dLight.PointLight;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen implements Screen {
	public static final float SCREEN_HEIGHT = 425;
	public static final float PPU = Gdx.graphics.getHeight() / SCREEN_HEIGHT;
	public static final float SCREEN_WIDTH = Gdx.graphics.getWidth() / PPU;
	private Game game;
	private OrthographicCamera camera;
	private PooledEngine pooledEngine;
	private Assets assets;
	private GameWorld world;

	public GameScreen(Game game) {
		this.game = game;
		camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		pooledEngine = new PooledEngine();
		assets = new Assets();

		assets.manager.finishLoading(); //Wait for the asset manager finish loading

		camera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
		pooledEngine.addSystem(new RenderSystem(camera, assets));
		DamnMain.rayHandler.setCombinedMatrix(camera.combined);
		world = new GameWorld(pooledEngine, assets);
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		pooledEngine.update(Gdx.graphics.getDeltaTime());

		//DamnMain.rayHandler.updateAndRender();
	}

	@Override
	public void dispose() {

	}
}