package com.folkscube.foots.basic;

import java.util.ArrayList;
import java.util.Iterator;


public class BoxManager {
	public static BoxManager manager;
	static{
		manager=new BoxManager();
	}
	public ArrayList<Box> list	=	null;
	
	public BoxManager(){
		list=new ArrayList<Box>();
	}
	
	public void putBox(Box b){
		if(b!=null) list.add(b);
	}
	
	public void createBox(float x,float y,float w,float h){
		Box box	=	new Box(x,y,w,h);
		list.add(box);
	}
	
	public boolean removeBox(float x,float y){
		Iterator<Box> iterator = list.iterator();
		while(iterator.hasNext()){
			Box box=iterator.next();
			if(box.x==x&&box.y==y){
				list.remove(box);
				return true;
			}
		}
		return false;
	}
	
	public void removeAll(){
		list.removeAll(list);
	}
	public void dispose(){
		removeAll();
	}
	public Box getBox(float x,float y){
		Box box=null;
		Iterator<Box> iterator = list.iterator();
		while(iterator.hasNext()){
			Box b = iterator.next();
			if(x>=b.x&&y>=b.y&&x<=b.x+b.w&&y<=b.y+b.h){
				box = b;
				return box;
			}
		}
		return null;
	}
	
}
