package com.folkscube.foots.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.utils.Array;
import com.folkscube.foots.basic.Box;
import com.folkscube.foots.basic.Mass;
import com.folkscube.foots.components.Laser;
import com.folkscube.foots.managers.BodyGenerator;
import com.folkscube.foots.renderers.BoxRenderer;

public class Player implements RayCastCallback{
	public Body ball		=	null;
	public Laser laser		=	null;
	private Color color		=	null;
	private Fixture fixture	=	null;
	public Vector2 l_e_p	=	new Vector2(); //Laser end position
	public Mass mass		=	null;
	public float av			=	0f;
	private Object obj		=	null;
	public float return_val	=	0f;
	public int count		=	0;
	public boolean stop_flag=	true;
	public Fixture current	=	null;
	public ArrayList<Vector2> casted = new ArrayList<Vector2>();
	private Box box			=	null;
	
	public Player(Body ball){
		this.ball			=	ball;
		laser				=	new Laser(this);
		Array<Fixture> list = 	ball.getFixtureList();
		fixture				=	list.get(0);
		Object o			=	new Object();
		this.obj			=	o;
		mass				=	new Mass();
		fixture.setUserData(obj);
		System.out.println("Initial");
	}
	public float getX(){
		return ball.getPosition().x;
	}
	public float getY(){
		return ball.getPosition().y;
	}
	public Vector2 getPosition(){
		return ball.getPosition();
	}
	public void render(SpriteBatch batch){
		
	}
	public void setLaserColor(Color color){
		this.color=color;
	}
	public void render(ShapeRenderer renderer){
		this.ball.setAngularVelocity(av);
		laser.update();
		laser.render(renderer,color);
		if(box!=null){
			box.n=0;
			if(!BoxRenderer.renderer.canRender) box.visible=false;
			box = BodyGenerator.getBox(current);
			box.visible	=	true;
			box.n=1;
		}
		if(box!=null) BoxRenderer.renderer.rend(box,renderer);
	}
	@Override
	public float reportRayFixture(Fixture fixture, Vector2 point,
			Vector2 normal, float fraction) {
		if(normal!=null){
			laser.setCasted(true);
			l_e_p.set(point.x,point.y);
			current = fixture;
			
			/*Body d	=	BodyGenerator.getBody(point.x,point.y);
			if(d!=null){
				current = d.getFixtureList().first();
				//System.out.println("Fixture :"+current);
			}
			*/
			/*if(!stop_flag){
				current	=	fixture;
				if(box!=null){
					box.n=0;
					if(!BoxRenderer.renderer.canRender) box.visible=false;
				}
				box =	BodyGenerator.getBox(current);
				box.visible = true;
				box.n=1;
				System.out.println("Fixture :"+fixture);
				stop_flag = true;
			}
			*/
		}
		return fraction;
	}
	public Body getCastedBody(){
		if(current ==null) return null;
		return current.getBody();
	}
}
