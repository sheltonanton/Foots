package com.folkscube.foots.managers;

import com.badlogic.gdx.physics.box2d.World;
import com.folkscube.foots.screens.GameScreen;

public class BodyManager {

	World world;
	GameScreen screen;
	
	public BodyManager(World world,GameScreen screen){
		this.world=world;
		this.screen=screen;
	}
	
}
