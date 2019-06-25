package com.firestorm.rendering.UI;

import com.firestorm.rendering.textures.Texture;

public class SplashScreenDriver {

	private SplashScreen screen;
	
	public SplashScreenDriver()
	{
		screen = new SplashScreen(new Texture("splashscreen"));
		screen.setLocationRelativeTo(null);
		screen.setMaxProgress(1000);
		screen.setVisible(true);
	}
}
