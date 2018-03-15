package com.folkscube.foots.renderers;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.folkscube.foots.basic.Box;
import com.folkscube.foots.basic.BoxManager;
import com.folkscube.foots.controllers.EditController;
import com.folkscube.foots.screens.GameScreen;


public class BoxRenderer {
	private Color color	= new Color().set(0, 0, 0.9f, 0.2f);
	public static BoxRenderer renderer=null;
	
	private Color green	= new Color().set(0, 0.9f, 0, 0.2f);
	private Color red	= new Color().set(0.9f, 0, 0, 0.2f);
	
	public Box selected;
	public boolean canRender=true;
	
	static{
		renderer=new BoxRenderer();
	}
	
	public BoxRenderer(){
		selected=new Box(0,0,GameScreen.OBW,GameScreen.OBW);
	}
	public void setColor(Color color){
		this.color=color;
	}
	public void render(ShapeRenderer r){
		if(!canRender) return;
		r.setColor(color);
		BoxManager m=BoxManager.manager;
		ArrayList<Box> list=m.list;
		Iterator<Box> iterator=list.iterator();
		while(iterator.hasNext()){
			Box box=iterator.next();
			if(box.visible) rend(box,r); 
		}
		r.setColor(getColor());
		if(selected!=null&&selected.visible) rend(selected,r);
	}
	public void rend(Box x,ShapeRenderer r){
		Color co = r.getColor();
		if(x.n!=0){
			r.setColor(Color.GOLD);
		}
		r.line(x.x,x.y,x.x+x.w,x.y);
		r.line(x.x,x.y,x.x,x.y+x.h);
		r.line(x.x+x.w,x.y+x.h,x.x+x.w,x.y);
		r.line(x.x+x.w,x.y+x.h,x.x,x.y+x.h);
		r.line(x.x,x.y,x.x+x.w,x.y+x.h);
		r.line(x.x,x.y+x.h,x.x+x.w,x.y);
		r.setColor(co);
	}
	public Color getColor(){
		if(!EditController.adtoggle) return green; else return red;
	}
}
