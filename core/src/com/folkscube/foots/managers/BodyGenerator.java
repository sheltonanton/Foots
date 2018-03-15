package com.folkscube.foots.managers;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.folkscube.foots.basic.Box;
import com.folkscube.foots.basic.BoxManager;
import com.folkscube.foots.basic.Conv;
import com.folkscube.foots.basic.SavedObject;
import com.folkscube.foots.player.UserData;

public class BodyGenerator {
	private static ArrayList<Body> list;
	static{
		list=new ArrayList<Body>();
	}
	public static void generate(Box box){
		Iterator<Body> iterator=list.iterator();
		while(iterator.hasNext()){
			Body body=iterator.next();
			if(body.getPosition().x==Conv.getrx(box.x,box.w)&&body.getPosition().y==Conv.getry(box.y,box.h)){
				if(!box.visible){
					//delete the body when the box is not visible 
					if(list.contains(body)) SavedObject.world.destroyBody(body);
					list.remove(body);
				}
				return;
			}
		}
		
		if(!box.visible) return;
		//create the body with visibility
		SavedObject.bxdef.position.x=Conv.getrx(box.x,box.w);
		SavedObject.bxdef.position.y=Conv.getry(box.y,box.h);
		Body b = SavedObject.world.createBody(SavedObject.bxdef);
		b.setUserData((Texture)TextureManager.manager.getTexture("pb1"));
		Fixture f=b.createFixture(SavedObject.fxdef);
		UserData ud	=	new UserData();
		ud.laser_ray_cast=true;
		f.setUserData(ud);
		list.add(b);
	}
	public static void generateAll(){
		Iterator<Box> iterator=BoxManager.manager.list.iterator();
		while(iterator.hasNext()){
			Box box=iterator.next();
			generate(box);
		}
	}
	public static Box getBox(Fixture fixture){
		Box b		=	null;
		Iterator<Body> iterator = list.iterator();
		while(iterator.hasNext()){
			Body body	=	iterator.next();
			Iterator<Fixture> i	= body.getFixtureList().iterator();
			while(i.hasNext()){
				Fixture f=i.next();
				if(f.equals(fixture)){
					Iterator <Box> boxi 	= BoxManager.manager.list.iterator();
					while(boxi.hasNext()){
						b = boxi.next();
						if(body.getPosition().x==Conv.getrx(b.x,b.w)&&body.getPosition().y==Conv.getry(b.y,b.h)){
							return b;
						}
					}
					
				}
			}
		}
		return b;
	}
	public static Body getBody(Box b){
		Body bd 	=	null;
		Body body	=	null;
		Iterator<Body> iterator	=	list.iterator();
		while(iterator.hasNext()){
			body	=	iterator.next();
			if(body.getPosition().x==Conv.getrx(b.x,b.w)&&body.getPosition().y==Conv.getry(b.y,b.h)){
				bd = body;
				break;
			}
		}
		return bd;
	}
	public static Body getBody(float x,float y){
		Body b 	= 	null;
		Box bx	=	null;
		Iterator<Box> iterator = BoxManager.manager.list.iterator();
		while(iterator.hasNext()){
			Box t	=	iterator.next();
			if(x>=t.x&&x<=t.x+t.w&&y>=t.y&&y<=t.y+t.h){
				bx = t;
				b	=	getBody(bx);
				break;
			}
		}
		return b;
	}
}

