package com.mycompany.a3;
import java.util.*;


public abstract class MovObjects extends GameObject implements IMovObjects{
	private int speed;
	private int direction;
	private int dirMarkX;
	private int dirMarkY;


	
	Random rand = new Random();
	//Moving object constructor, sets default speed and direction of objects
	public MovObjects(){
		speed = rand.nextInt(10);
		direction = rand.nextInt(359);
		dirMarkX = 1;
		dirMarkY = 1;
	}
	//sets the new locations of objects
	public void move(int width, int height, int time){
		int angle = 90 - direction;
		if(getLocX() >= width - getSize() || getLocX() <= 0){
			dirMarkX = -(dirMarkX);
			
		}
		if(getLocY() >= height - getSize()|| getLocY() <= 0){
			dirMarkY = -(dirMarkY);
		}
		double deltaX = dirMarkX*(((double)Math.cos(Math.toRadians(angle))) * speed * time/250);
		double deltaY = dirMarkY*(((double)Math.sin(Math.toRadians(angle))) * speed * time/250);
		super.move(deltaX, deltaY);
	}
	//returns the current speed of object
	public int getSpeed(){
		return speed;
	}
	//sets the current speed of object
	public void setSpeed(int speed){
		this.speed = speed;
	}
	//gets the current direction objects
	public int getDirection(){
		return direction;
		
	}
	//sets the direction of objects
	public void setDirection(int direction){
		this.direction = direction;
		
	}
	//prints out speed, direction, and parent toString()
	public String toString(){
		return super.toString() + ", speed="+ this.getSpeed() + ", direction=" + this.getDirection(); 
	}

}
