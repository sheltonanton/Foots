package com.folkscube.foots.basic;

public class Box {
	public float x;
	public float y;
	public float w;
	public float h;
	
	public boolean selected=false;
	public int side=0;
	public boolean dselected=false;
	public boolean visible=false;
	
	public static final int NONE	=	0;
	public static final int LEFT	=	1;
	public static final int RIGHT	=	2;
	public static final int TOP		=	3;
	public static final int BOTTOM	=	4;
	public int n					=	0;
	
	public Box(float x,float y,float w,float h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		visible=true;
		/*
		System.out.println("Box created at: ");
		System.out.println("X: "+this.x);
		System.out.println("Y: "+this.y);
		System.out.println("W: "+this.w);
		System.out.println("H: "+this.h);
		System.out.println();
		System.out.println("-----------------");
		System.out.println();
		*/
	}
	
}
