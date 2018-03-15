package com.folkscube.foots.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.folkscube.foots.basic.SavedObject;
import com.folkscube.foots.managers.ShaderManager;
import com.folkscube.foots.screens.GameScreen;

public class BodyRenderer {
	public static BodyRenderer renderer = null;
	static{
		renderer = new BodyRenderer();
	}
	public void render(SpriteBatch batch){
		World world = SavedObject.world;
		Array<Body> bodies 	=	new Array<Body>();
		world.getBodies(bodies);
		for(Body b: bodies){
			ShaderProgram program = ShaderManager.manager.getShaderProgram(b.toString());
			if(b.getUserData()!=null){
			if(program != null)	{
				program.begin();
				program.setUniformf("u_viewportInverse", new Vector2(1f / (Gdx.graphics.getWidth()/GameScreen.PPM), 1f / (Gdx.graphics.getHeight()/GameScreen.PPM)));
				program.setUniformf("u_offset", 0.1f);
				program.setUniformf("u_step", 0.01f);
				program.setUniformf("u_color", new Vector3(1f, 1f, 1f));
				program.end();
				batch.setShader(program);
			}
			batch.begin();
			batch.draw((Texture)b.getUserData(), b.getPosition().x, b.getPosition().y);
			batch.end();
			batch.setShader(null);
			}
			ShaderManager.manager.removeShaderProgram(b.toString());
		}
	}
}
