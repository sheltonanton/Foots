package com.folkscube.foots.managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class TextureManager {
	public static TextureManager manager = null;
	static{
		manager = new TextureManager();
	}
	HashMap<String, Texture> list = null;
	
	public TextureManager(){
		list = new HashMap<String, Texture>();
	}
	public Texture getTexture(String name){
		return list.get(name);
	}
	public void addTexture(String name, Texture texture){
		list.put(name,texture);
	}
	public void loadTexture(String name, String filename){
		list.put(name,new Texture(Gdx.files.internal(filename)));
	}
	public void removeTexture(String name){
		Texture texture = null;
		texture = list.get(name);
		if(texture!=null){
			list.remove(texture);
		}
	}
}
