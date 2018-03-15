package com.folkscube.foots.basic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public class Mass {
	private float mass;
	public int type;
	public float px,py;
	public boolean isFlashable	=	false;
	public boolean isMovable	=	false;
	public Body body			=	null;
	
	public Mass(){
		
	}
	public Mass(Body body){
		this.body=body;
	}
	public Mass(Body body, float mass){
		this.body=body;
		this.mass=mass;
	}
	public void onFocus(){
		
	}
	public void onFired(){
		
	}
	public void onDraw(){
		
	}
	public void setMass(float mass){
		this.mass=mass;
	}
	public float getMass(){
		return this.mass;
	}
	public void draw(SpriteBatch batch){
		
	}
}
