package com.nightglyphstudio.damn405;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class DamnMain extends Game {
	public static GameScreen gameScreen;
	public static Box2DDebugRenderer debugRenderer; //Debugger for box2d. TODO: Remove before release.
	public static RayHandler rayHandler; //Box2D lights.
	public static World box2dWorld; //Box2D.
	public static Engine ashleyEngine; //Ashley entity system.

	@Override
	public void create () {
		debugRenderer = new Box2DDebugRenderer();
		box2dWorld = new World(new Vector2(0, -10), true);
		rayHandler = new RayHandler(box2dWorld);
		ashleyEngine = new Engine();
		gameScreen = new GameScreen(this);
		setScreen(gameScreen); //Set the initial screen to the game screen for now. Menu later.
	}

	@Override
	public void render () {
		super.render();
	}
}