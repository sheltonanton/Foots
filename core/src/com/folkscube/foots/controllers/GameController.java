package com.folkscube.foots.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.folkscube.foots.basic.SavedObject;
import com.folkscube.foots.player.Player;


public class GameController extends SubController{

	Player player 			=	null;
	GameController(){
		this.player			=	SavedObject.player;
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		switch(keycode){
		case Input.Keys.D:{player.av=(float)-Math.PI*8;break;}
		case Input.Keys.A:{player.av=(float)Math.PI*8;break;}
		case Input.Keys.S:{break;}
		case Input.Keys.W:{break;}
		default:;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
		case Input.Keys.D:{player.av=(float)0;break;}
		case Input.Keys.A:{player.av=(float)0;break;}
		case Input.Keys.S:{break;}
		case Input.Keys.W:{break;}
		default:;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 v		=	new Vector3(screenX,screenY,0);
		SavedObject.world_camera.unproject(v);
		float fx,fy,ix,iy;
		fx=v.x;
		fy=v.y;
		ix=player.laser.getStartPosition().x;
		iy=player.laser.getStartPosition().y;
		player.laser.setAngle(getAngle(ix,iy,fx,fy));
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 v		=	new Vector3(screenX,screenY,0);
		SavedObject.world_camera.unproject(v);
		float fx,fy,ix,iy;
		fx=v.x;
		fy=v.y;
		ix=player.laser.getStartPosition().x;
		iy=player.laser.getStartPosition().y;
		player.laser.setAngle(getAngle(ix,iy,fx,fy));
		player.laser.startCasting();
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	public float getAngle(float x1,float y1,float x,float y) {
	    float angle = (float) Math.toDegrees(Math.atan2(y - y1,x - x1));

	    if(angle < 0){
	        angle += 360;
	    }
	    return angle;
	}

}
