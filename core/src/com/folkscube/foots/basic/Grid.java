package com.folkscube.foots.basic;

import java.math.BigDecimal;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.folkscube.foots.screens.GameScreen;

public class Grid {

	private int x;
	private int y;
	private float w;
	private float h;
	private int nx;
	private int ny;
	private Color color;
	
	public Grid(int x,int y,int nx,int ny,float w,float h,Color color){
		this.x=x;
		this.y=y;
		this.nx=nx;
		this.ny=ny;
		this.w=w;
		this.h=h;
		this.color=color;
	}
	public void drawGrid(ShapeRenderer r){
		
		r.setColor(color);
		for(int i=0;i<nx;i++){
			r.line(x+(i*w),y,x+(i*w),y+(ny*h));
		}
		for(int i=0;i<ny;i++){
			r.line(x,y+(i*h),x+(nx*w),y+(i*h));
		}
	}
	public static float getX(float tx){
		BigDecimal bd=new BigDecimal(tx*GameScreen.PPM).divideToIntegralValue(new BigDecimal(GameScreen.BW));
		int t=bd.intValue();
		return t*GameScreen.BW/GameScreen.PPM;
	}
	public static float getY(float ty){
		BigDecimal bd=new BigDecimal(ty*GameScreen.PPM).divideToIntegralValue(new BigDecimal(GameScreen.BW));
		int t=bd.intValue();
		return t*GameScreen.BW/GameScreen.PPM;
	}
}
