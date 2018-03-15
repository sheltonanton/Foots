package com.folkscube.foots.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.folkscube.foots.basic.SavedObject;
import com.folkscube.foots.managers.ShaderManager;
import com.folkscube.foots.player.Player;

public class Laser{
	public static final float INFINITY = 0f;
	
	private Vector2 p1=new Vector2();
	private float range = 2f;
	private float angle = 90;
	private Player player;
	private Vector2 p2=new Vector2();
	private boolean casted =	false;
	
	public Laser(int x,int y,int angle){
		p1.set(x,y);
		this.angle=angle;
	}
	public Laser(Player player){
		this.player=player;
	}
	public void setStartPosition(int x,int y){
		this.p1.set(x,y);
	}
	public void setEndPosition(float x,float y){
		this.p2.set(x,y);
	}
	public Vector2 getStartPosition(){
		return p1;
	}
	public Vector2 getEndPosition(){
		return p2;
	}
	public void setAngle(float angle){
		this.angle=angle;
	}
	public float getAngle(){
		return this.angle;
	}
	public void setRange(float range){
		this.range=range;
	}
	public float getRange(){
		return this.range;
	}
	public void setCasted(boolean casted){
		this.casted=casted;
	}
	public void startCasting(){
		p1.set(player.ball.getPosition().x,player.ball.getPosition().y);
		float fx,fy;
		fx=p1.x+range*(float)Math.cos(rad(angle));
		fy=p1.y+range*(float)Math.sin(rad(angle));
		p2.set(fx,fy);
		if (player.current!=null)
		ShaderManager.manager.removeShaderProgram(player.current.getBody().toString());
		SavedObject.world.rayCast(player, p1, p2);
		if(player.current!=null){
			if(ShaderManager.manager.getShaderProgram(player.getCastedBody().toString())==null)
				ShaderManager.manager.addShaderProgram(player.getCastedBody().toString(), ShaderManager.manager.SHAPE_OUTLINE);
		}
		if(casted){
			p2.set(player.l_e_p.x,player.l_e_p.y);
			System.out.println("X: "+p2.x+" Y: "+p2.y);
			System.out.println(player.getCastedBody().getFixtureList().first());
			casted	=	false;
		}
	}
	public void update(){
		/*p1.set(player.ball.getPosition().x,player.ball.getPosition().y);
		float fx,fy;
		fx=p1.x+range*(float)Math.cos(rad(angle));
		fy=p1.y+range*(float)Math.sin(rad(angle));
		p2.set(fx,fy);
		*/
		//player.stop_flag = false;
		/*if(casted){
			p2.set(player.l_e_p.x,player.l_e_p.y);
		}
		*/
	}
	public void render(ShapeRenderer s, Color color){
		s.setColor(color);
		s.line(p1,p2);
	}
	
	//Secondary functions
	public double rad(float angle){
		return angle*Math.PI/180;
	}
}
