package com.folkscube.foots.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainController implements InputProcessor{

	InputMultiplexer multi=new InputMultiplexer();
	EditController edit = new EditController();
	GameController game	= new GameController();
	
	BitmapFont font=new BitmapFont();
	
	private boolean bedit=false;
	private boolean bgame=false;
	
	public MainController(){
		edit.init();
		
		multi.addProcessor(this);
		Gdx.input.setInputProcessor(multi);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(bedit) edit.keyDown(keycode); 
		if(bgame) game.keyDown(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(bedit) edit.keyUp(keycode);
		if(bgame) game.keyUp(keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		if(character=='0'){
			toggleEditController();
			return false;
		}
		if(character=='9'){
			toggleGameController();
			return false;
		}
		if(bedit) edit.keyTyped(character);
		if(bgame) game.keyTyped(character);
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(bedit) edit.touchDown(screenX, screenY, pointer, button);
		if(bgame) game.touchDown(screenX, screenY, pointer, button);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(bedit) edit.touchUp(screenX, screenY, pointer, button);
		if(bgame) game.touchUp(screenX, screenY, pointer, button);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(bedit) edit.touchDragged(screenX, screenY, pointer);
		if(bgame) game.touchDragged(screenX, screenY, pointer);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(bedit) edit.mouseMoved(screenX, screenY);
		if(bgame) game.mouseMoved(screenX, screenY);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if(bedit) edit.scrolled(amount);
		if(bgame) game.scrolled(amount);
		return false;
	}

	public void toggleEditController(){
		if(bedit){
			bedit=false;
			edit.dispose();
		}else{
			bedit=true;
			edit.init();
		}
	}
	public void toggleGameController(){
		if(bgame) { 
			bgame=false;
			game.dispose();
		}else {
			bgame=true;
		}
	}
	public void render(SpriteBatch batch){
		font.setColor(Color.WHITE);
		font.draw(batch,"Edit:    "+((bedit)?"Enabled":"Disabled"),20,Gdx.graphics.getHeight()-20);
		font.draw(batch,"Game:    "+((bgame)?"Enabled":"Disabled"),20,Gdx.graphics.getHeight()-40);
		
		if(bedit) edit.render(batch);
		if(bgame) game.render(batch);
	}
	public void dispose(){
		Gdx.input.setInputProcessor(null);
		font.dispose();
	}
}
