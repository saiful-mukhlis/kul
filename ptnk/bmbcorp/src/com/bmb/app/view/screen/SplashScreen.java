package com.bmb.app.view.screen;

public class SplashScreen extends QSplashScreen {

	public SplashScreen() {
		super();
		buildComponent();
	}

	@Override
	protected void initComponent() {
		width = 400;
		height = 249;
		pathImageResource="/image/screen.jpg";

	}



}