package com.nightglyphstudio.damn405;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class GameWorld extends EntitySystem {
	private PooledEngine pooledEngine;
	private Assets assets;

	public GameWorld(PooledEngine pooledEngine, Assets assets) {
		this.pooledEngine = pooledEngine;
		this.assets = assets;
		loadEntities();
	}

	public void loadEntities() {
		Texture freewayTexture = assets.manager.get("Freeway.png", Texture.class);
		Entity freeway = pooledEngine.createEntity();
		freeway.add(new PositionComponent(0, 50));
		freeway.add(new MovementComponent(0, 0));
		freeway.add(new VisualComponent(new TextureRegion(freewayTexture)));
		pooledEngine.addEntity(freeway);

		Texture carTexture = assets.manager.get("ShivyWarship.png", Texture.class);
		for (int i = 0; i < 20; i++) {
			Entity car = pooledEngine.createEntity();
			car.add(new PositionComponent(MathUtils.random(GameScreen.SCREEN_WIDTH), 50));
			car.add(new MovementComponent(1, MathUtils.random(50)));
			car.add(new VisualComponent(new TextureRegion(carTexture)));
			pooledEngine.addEntity(car);
		}
	}

	@Override
	public void update(float deltaTime) {
		for (int i = 0; i < pooledEngine.getEntities().size(); i++) {

		}
	}
}
