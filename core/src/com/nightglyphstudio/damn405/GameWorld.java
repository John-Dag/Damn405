package com.nightglyphstudio.damn405;

import box2dLight.PointLight;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
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
		Texture carTexture = assets.manager.get("ShivyWarship.png", Texture.class);
		for (int i = 0; i < 5; i++) {
			float carPositionX = MathUtils.random(GameScreen.SCREEN_WIDTH);
			float carMovementY = MathUtils.random(50);
			Entity car = pooledEngine.createEntity();
			car.add(new PositionComponent(carPositionX, 50));
			car.add(new MovementComponent(1, carMovementY));
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
