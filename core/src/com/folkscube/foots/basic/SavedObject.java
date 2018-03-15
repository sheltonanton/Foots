package com.folkscube.foots.basic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.folkscube.foots.player.Player;
import com.folkscube.foots.screens.GameScreen;

public class SavedObject {
	public static World world =null;
	public static OrthographicCamera world_camera=null;
	public static BodyDef bxdef=null;
	public static FixtureDef fxdef=null;
	public static BodyDef lbxdef=null;
	public static FixtureDef lfxdef=null;
	public static Body colliderBody=null;
	public static Player player=null;
	public static GameScreen game_screen=null;
}
