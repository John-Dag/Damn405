package com.nightglyphstudio.damn405;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	public AssetManager manager;

	public Assets() {
		manager = new AssetManager();
		loadAssets();
	}

	public void loadAssets() {
		manager.load("Freeway.png", Texture.class);
	}
}
