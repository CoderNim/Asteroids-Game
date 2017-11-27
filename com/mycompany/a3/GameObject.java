package com.mycompany.a3;


import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;


public abstract class GameObject implements IDrawable{
	private double locationX;
	private double locationY;
	private int myColor;
	private int size;
	
	//initiate initial object location to center
	public GameObject(){
		myColor = ColorUtil.rgb(255, 0, 0);
		size = 5;
	}
	//set the object location
	public void setLocation(double x, double y){
		locationX = x;
		locationY = y;
	}
	//get the x value location
	public double getLocX(){
		return locationX;
	}
	//get the y value location
	public double getLocY(){
		return locationY;
	}
	public int getSize(){
		return size;
	}
	public void setSize(int size){
		this.size = size;
	}
	
	//move based on the direction of the object 
	public void move(double deltaX, double deltaY){
		double newX = Math.round(((getLocX() + deltaX)*10.0)/10.0);
		double newY = Math.round(((getLocY() + deltaY)*10.0)/10.0);
		setLocation(newX, newY);
	}
	//set the color
	public void setColor(int color1, int color2, int color3){
		myColor = ColorUtil.rgb(color1, color2, color3);
	}
	//get the color
	public int getColor(){
		return myColor;
	}
	//print out location and color of object
	public String toString(){
		return "Loc:" + "(" + getLocX() + ", " + getLocY() + ")" + ", color:" + "[" + ColorUtil.red(myColor) + ", " + ColorUtil.green(myColor) + ", " + ColorUtil.blue(myColor) + "]";
	}
}
