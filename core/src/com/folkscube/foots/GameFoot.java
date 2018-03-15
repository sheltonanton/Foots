package com.folkscube.foots;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.folkscube.foots.screens.*;

public class GameFoot extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(this));
	}
}
