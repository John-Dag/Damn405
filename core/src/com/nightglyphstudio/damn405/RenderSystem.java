package com.nightglyphstudio.damn405;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderSystem extends EntitySystem {
	private ImmutableArray<Entity> entities;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private ComponentMapper<PositionComponent> pm;
	private ComponentMapper<VisualComponent> vm;
	private ComponentMapper<MovementComponent> mm;
	private Assets assets;

	public RenderSystem(OrthographicCamera camera, Assets assets) {
		batch = new SpriteBatch();
		pm = ComponentMapper.getFor(PositionComponent.class);
		vm = ComponentMapper.getFor(VisualComponent.class);
		mm = ComponentMapper.getFor(MovementComponent.class);
		this.camera = camera;
		this.assets = assets;
	}

	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VisualComponent.class, MovementComponent.class).get());
	}

	@Override
	public void removedFromEngine(Engine engine) {

	}

	@Override
	public void update(float deltaTime) {
		PositionComponent positionComponent;
		VisualComponent visualComponent;
		MovementComponent movementComponenet;

		if (assets.manager.update()) {

		}

		camera.update();

		batch.begin();
		batch.setProjectionMatrix(camera.combined);

		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);

			positionComponent = pm.get(e);
			visualComponent = vm.get(e);
			movementComponenet = mm.get(e);

			positionComponent.positionX += movementComponenet.velocityX * deltaTime;
			positionComponent.positionY += movementComponenet.velocityY * deltaTime;

			batch.draw(visualComponent.region, positionComponent.positionX, positionComponent.positionY);
		}

		batch.end();
	}
}
