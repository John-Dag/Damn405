package com.nightglyphstudio.damn405.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nightglyphstudio.damn405.DamnMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Damn405";
		config.height = 800;
		config.width = 480;
		new LwjglApplication(new DamnMain(), config);
	}
}
