package com.folkscube.foots.managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ShaderManager {
	public static ShaderManager manager = null;
	public ShaderProgram SHAPE_OUTLINE = null;
	static{
		manager = new ShaderManager();
	}
	HashMap<String, ShaderProgram> list = null;
	public ShaderManager(){
		list = new HashMap<String , ShaderProgram>();
		
	}
	public ShaderProgram getShaderProgram(String name){
		return list.get(name);
	}
	public void addShaderProgram(String name, ShaderProgram program){
		list.put(name,program);
	}
	public ShaderProgram loadShader(String vertex, String fragment) {
		ShaderProgram program = null;
		String vertexShader;
		String fragmentShader;
		vertexShader = Gdx.files.internal(vertex).readString();
		fragmentShader = Gdx.files.internal(fragment).readString();
		program = new ShaderProgram(vertexShader, fragmentShader);
		if (!program.isCompiled()) throw new GdxRuntimeException("Couldn't compile shader: " + program.getLog());
		return program;
	}
	public void removeShaderProgram(String name){
		list.remove((String)name);
	}
}
