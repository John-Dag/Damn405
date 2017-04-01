package com.nightglyphstudio.damn405;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.swing.text.Position;

public class RenderSystem extends EntitySystem {
	public static final float FREEWAY_VELOCITY_Y = 2.0f;

	private ImmutableArray<Entity> carsArray;
	private Family carsFamily;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private ComponentMapper<PositionComponent> pm;
	private ComponentMapper<VisualComponent> vm;
	private ComponentMapper<MovementComponent> mm;
	private Assets assets;
	private Texture headlightTexture;
	private Sprite headlightSprite;
	private float freewayPositionY;
	private float timeSinceLastUpdate;
	private float frameTime = 1/60;

	public RenderSystem(OrthographicCamera camera, Assets assets) {
		batch = new SpriteBatch();
		carsFamily = Family.all(PositionComponent.class, MovementComponent.class,
						  VisualComponent.class, HeadlightComponent.class).get();
		pm = ComponentMapper.getFor(PositionComponent.class);
		vm = ComponentMapper.getFor(VisualComponent.class);
		mm = ComponentMapper.getFor(MovementComponent.class);
		this.camera = camera;
		this.assets = assets;
		headlightTexture = assets.manager.get("pointlight.png");
		headlightSprite = new Sprite(headlightTexture);
		freewayPositionY = 0;
		timeSinceLastUpdate = 0;
	}

	@Override
	public void addedToEngine(Engine engine) {
		carsArray = engine.getEntitiesFor(carsFamily);
	}

	@Override
	public void removedFromEngine(Engine engine) {

	}

	@Override
	public void update(float deltaTime) {
		if (timeSinceLastUpdate < frameTime) {
			return;
		} else {
			timeSinceLastUpdate += deltaTime;
		}
		PositionComponent positionComponent;
		VisualComponent visualComponent;
		MovementComponent movementComponent;

		if (assets.manager.update()) {

		}

		camera.update();

		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		Texture freewayTexture = assets.manager.get("Freeway.png", Texture.class);
		batch.draw(freewayTexture, 0, freewayPositionY -= FREEWAY_VELOCITY_Y);
		if (freewayPositionY < (GameScreen.SCREEN_HEIGHT - freewayTexture.getHeight())) {
			batch.draw(freewayTexture, 0, freewayPositionY + freewayTexture.getHeight());
		}
		if (freewayPositionY < (freewayTexture.getHeight() * -1)) {
			freewayPositionY = 0;
		}

		for (int i = 0; i < carsArray.size(); i++) {
			Entity e = carsArray.get(i);

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
