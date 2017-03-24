package com.nightglyphstudio.damn405;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen implements Screen {
	public static final int SCREEN_WIDTH = 480;
	public static final int SCREEN_HEIGHT = 800;
	private Game game;
	private OrthographicCamera camera;
	private PooledEngine pooledEngine;
	private Assets assets;
	private Texture freeway;

	public GameScreen(Game game) {
		this.game = game;
		camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		pooledEngine = new PooledEngine();
		assets = new Assets();

		assets.manager.finishLoading();

		camera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
		pooledEngine.addSystem(new RenderSystem(camera, assets));
		Texture freewayTexture = assets.manager.get("Freeway.png", Texture.class);
		Entity freeway = pooledEngine.createEntity();
		freeway.add(new PositionComponent(0, 0));
		freeway.add(new VisualComponent(new TextureRegion(freewayTexture)));
		pooledEngine.addEntity(freeway);
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
	}

	@Override
	public void dispose() {

	}
}