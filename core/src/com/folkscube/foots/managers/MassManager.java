package com.folkscube.foots.managers;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.folkscube.foots.basic.Mass;

public class MassManager {
	public HashMap<String, Mass> list=null;
	
	public MassManager(){
		list=new HashMap<String, Mass>();
	}
	public Mass getMass(String name){
		return list.get(name);
	}
	public void setMass(String name,Mass mass){
		list.put(name,mass);
	}
	public void draw(SpriteBatch batch){
		//TODO draw each of the mass
	}
}
