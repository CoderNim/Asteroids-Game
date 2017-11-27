package com.mycompany.a3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;

public class Asteroid extends MovObjects implements ISelectable, ICollide {
	private int size;
	private Random rand = new Random();
	private boolean isSelected;
	private boolean result;

	// Constructor for asteroid, sets color, size, speed, direction, and
	// location to random integers.
	public Asteroid(int locX, int locY) {
		result = false;
		setColor(0, 0, 0);
		setSize(rand.nextInt(20) + 15);
		setSpeed(rand.nextInt(6) + 4);
		setDirection(rand.nextInt(359));
		setLocation(rand.nextInt(locX - getSize() * 3) + getSize() * 2, rand.nextInt(locY - getSize() * 3) + getSize()*2);
	}
	//draw an asteroid
	public void draw(Graphics g, Point relsPoint) {
		int xCenter = (int) relsPoint.getX() + (int) getLocX();
		int yCenter = (int) relsPoint.getY() + (int) getLocY();
		if (isSelected()) {
			g.setColor(this.getColor());
			g.drawArc(xCenter, yCenter, (int) getSize(), (int) getSize(), 0, 360);
		} else {
			g.setColor(this.getColor());
			g.fillArc(xCenter, yCenter, (int) getSize(), (int) getSize(), 0, 360);
		}
	}

	// prints the size of the asteroid + parent toString()
	public String toString() {
		return "Asteroid: " + super.toString() + ", size:" + getSize();
	}

	public void move() {

	}
	//asteroid is selected
	public void setSelected(boolean yesNo) {
		isSelected = yesNo;
	}
	//returns selected value
	public boolean isSelected() {
		return isSelected;
	}

	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int x = (int) pPtrRelPrnt.getX();
		int y = (int) pPtrRelPrnt.getY();
		int xLoc = (int) pCmpRelPrnt.getX() + (int) getLocX();
		int yLoc = (int) pCmpRelPrnt.getY() + (int) getLocY();
		if ((x >= xLoc ) && (x <= xLoc + getSize()) && (y >= yLoc) && (y <= yLoc + getSize())) {
			return true;
		} else {
			return false;
		}
	}
	//set asteroid locatoin
	public void setLocation(int x, int y) {
		this.setLocation((double) x, (double) y);
	}

	public boolean collidesWith(GameObject otherObject) {


		
		boolean result = false;
		
		int thisCenterX = (int) (this.getLocX() + (this.getSize()/2)); // find centers
		int thisCenterY = (int) (this.getLocY() + (this.getSize()/2));
		
		int otherCenterX = (int) (((GameObject) otherObject).getLocX() + (this.getSize()/2));
		int otherCenterY = (int) (((GameObject) otherObject).getLocY() + (this.getSize()/2));
		
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		distBetweenCentersSqr = (int) Math.sqrt(distBetweenCentersSqr);
		
		// find square of sum of radii
		int thisRadius = this.getSize();
		int otherRadius = otherObject.getSize();
		int radiiSqr = thisRadius + otherRadius;
		
		if (distBetweenCentersSqr <= radiiSqr) { 
			result = true ; 
		}
		return result ;
		
	}
		 
	public void handleCollision(GameObject otherObject) {
		System.out.println("There has been a collision");

	}

}
