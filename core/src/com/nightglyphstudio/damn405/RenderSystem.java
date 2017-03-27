package com.nightglyphstudio.damn405;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderSystem extends EntitySystem {
	private ImmutableArray<Entity> entities;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private ComponentMapper<PositionComponent> pm;
	private ComponentMapper<VisualComponent> vm;
	private ComponentMapper<MovementComponent> mm;
	private Assets assets;
	private Texture headlightTexture;
	private Sprite headlightSprite;

	public RenderSystem(OrthographicCamera camera, Assets assets) {
		batch = new SpriteBatch();
		pm = ComponentMapper.getFor(PositionComponent.class);
		vm = ComponentMapper.getFor(VisualComponent.class);
		mm = ComponentMapper.getFor(MovementComponent.class);
		this.camera = camera;
		this.assets = assets;
		headlightTexture = assets.manager.get("pointlight.png");
		headlightSprite = new Sprite(headlightTexture);
	}

	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(PositionComponent.class, MovementComponent.class, VisualComponent.class).get());
	}

	@Override
	public void removedFromEngine(Engine engine) {

	}

	@Override
	public void update(float deltaTime) {
		PositionComponent positionComponent;
		VisualComponent visualComponent;
		MovementComponent movementComponent;

		if (assets.manager.update()) {

		}

		camera.update();

		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		Texture freewayTexture = assets.manager.get("Freeway.png", Texture.class);
		batch.draw(freewayTexture, 0, 50);

		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);

			positionComponent = pm.get(e);
			visualComponent = vm.get(e);
			movementComponent = mm.get(e);

			positionComponent.positionX += movementComponent.velocityX * deltaTime;
			positionComponent.positionY += movementComponent.velocityY * deltaTime;

			batch.draw(visualComponent.region, positionComponent.positionX, positionComponent.positionY);
			batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);
			headlightSprite.draw(batch, 1);
			headlightSprite.setPosition(positionComponent.positionX - 7, positionComponent.positionY + 16);
			batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		}

		batch.end();
	}
}
