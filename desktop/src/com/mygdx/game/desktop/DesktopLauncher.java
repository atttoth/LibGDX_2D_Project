package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Application;
import static com.mygdx.game.utils.Constants.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)SCREEN_WIDTH;
		config.height = (int)SCREEN_HEIGHT;
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		new LwjglApplication(new Application(), config);
	}
}
