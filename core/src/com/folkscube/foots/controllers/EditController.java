package com.folkscube.foots.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.folkscube.foots.basic.Box;
import com.folkscube.foots.basic.BoxManager;
import com.folkscube.foots.basic.Grid;
import com.folkscube.foots.basic.SavedObject;
import com.folkscube.foots.managers.BodyGenerator;
import com.folkscube.foots.renderers.BoxRenderer;
import com.folkscube.foots.screens.GameScreen;

public class EditController extends SubController{

	BoxManager manager=null;
	public static volatile boolean adtoggle=false;
	private String cmd="";
	BitmapFont font=null;

	public void init(){
		BoxRenderer.renderer.selected.visible=true;
		font=new BitmapFont();
	}
	public EditController(){
		manager=BoxManager.manager;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		cmd=cmd+character;
		if(cmd.equals("gen")){
			cmd="generating";
			BodyGenerator.generateAll();
			cmd="";
			return false;
		}
		if(cmd.equals("add")){
			adtoggle=false;
			cmd="";
			return false;
		}
		if(cmd.equals("del")){
			adtoggle=true;
			cmd="";
			return false;
		}
		if(cmd.equals("one")){
			SavedObject.player.return_val=1f;
		}
		if(cmd.equals("neg")){
			SavedObject.player.return_val=-1f;
		}
		if(cmd.equals("non")){
			SavedObject.player.return_val=0f;
		}
		if(cmd.length()==3){
			cmd="";
			return false;
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		float x,y;
		screenY=Gdx.graphics.getHeight()-screenY;
		x=screenX/GameScreen.PPM;
		y=screenY/GameScreen.PPM;
		Box box=manager.getBox(x, y);
		if(box!=null){
			if(adtoggle){
				box.visible=false;
			}
			else{
				box.visible=true;
			}
		}
		if(box==null){ 
			if(!adtoggle){
				BoxManager.manager.createBox(Grid.getX(x),Grid.getY(y),GameScreen.OBW,GameScreen.OBW);
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		screenY=Gdx.graphics.getHeight()-screenY;
		float x=screenX/GameScreen.PPM;
		float y=screenY/GameScreen.PPM;
		Box box=manager.getBox(x, y);	//getting the box at position x,y screen selection
		if(box!=null){					//if not null and present at the cursor drag position....
			if(adtoggle){
				box.visible=false;			//setting the box to none visibility since it has been selected with delete cursor
			}
			else{
				box.visible=true;			//or simply set the box to full visibility since selected with add cursor
			}
		}
		if(box==null){					//if no box is present at x,y and selected with add cursor, a new box is created and added
			if(!adtoggle){
				BoxManager.manager.createBox(Grid.getX(x),Grid.getY(y),GameScreen.OBW,GameScreen.OBW);
			}
		}
		
		// to display the cursor box
		box=BoxRenderer.renderer.selected;
		box.x=Grid.getX(x);				//setting the cursor box x position
		box.y=Grid.getY(y);				//setting the cursor box y position
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		screenY=Gdx.graphics.getHeight()-screenY;
		float x=screenX/GameScreen.PPM;
		float y=screenY/GameScreen.PPM;
		Box box;
		box=BoxRenderer.renderer.selected; //used for making the cursor to display at the given position x,y
		box.x=Grid.getX(x);
		box.y=Grid.getY(y);
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	public void render(SpriteBatch batch){
		font.setColor(Color.WHITE);
		font.draw(batch,cmd,20,Gdx.graphics.getHeight()-60);
		BoxRenderer.renderer.canRender=true;
	}
	public void dispose(){
		BoxRenderer.renderer.selected.visible=false;
		font.dispose();
		BoxRenderer.renderer.canRender=false;
	}
}
