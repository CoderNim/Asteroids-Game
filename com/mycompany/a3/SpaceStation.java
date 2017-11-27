package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class SpaceStation extends FixedObjects implements ICollide{
	private int blink;
	private boolean light;
	private Random rand;

	// SpaceStation constructor, set blink rate, location, and color
	public SpaceStation(int locX, int locY) {
		super();
		setSize(15);
		rand = new Random();
		setColor(0, 255, 0);
		light = false;
		blink = rand.nextInt(10) + 5;
		setLocation(rand.nextInt(locX), rand.nextInt(locY));
	}

	// get the blink rate
	public int getBlink() {
		return blink;
	}

	// get
	public boolean getLight() {
		return light;
	}

	// Toggle the light on and off based on the game time and blink rate
	public void toggleLight(int curTime) {
		if (curTime % getBlink() == 0) {
			light ^= true;
		}
		if (getLight() == true) {
			System.out.println("Light is on");
		}
		if (getLight() == false) {
			System.out.println("Light is off");
		}
	}
	//draw a space station
	public void draw(Graphics g, Point relsPoint) {
		int xCenter = (int) relsPoint.getX() + (int) getLocX();
		int yCenter = (int) relsPoint.getY() + (int) getLocY();
		g.setColor(this.getColor());
		if (getLight() == true) {
			g.drawRect(xCenter - (this.getSize()/2), yCenter - (this.getSize()/2), this.getSize(), this.getSize());
		}
		if (getLight() == false) {
			g.fillRect(xCenter - (this.getSize()/2), yCenter - (this.getSize()/2), this.getSize(), this.getSize());
		}
	}

	// print blink rate + parent toString()
	public String toString() {
		return "Station: " + super.toString() + ", blink rate:" + getBlink();
	}

	public boolean collidesWith(GameObject otherObject) {

		boolean result = false;

		int thisCenterX = (int) (this.getLocX() + (this.getSize())); // find
																			// centers
		int thisCenterY = (int) (this.getLocY() + (this.getSize()));

		int otherCenterX = (int) (((GameObject) otherObject).getLocX() + (this.getSize()));
		int otherCenterY = (int) (((GameObject) otherObject).getLocY() + (this.getSize()));

		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx * dx + dy * dy);
		distBetweenCentersSqr = (int) Math.sqrt(distBetweenCentersSqr);

		// find square of sum of radii
		int thisRadius = this.getSize();
		int otherRadius = otherObject.getSize();
		int radiiSqr = thisRadius + otherRadius;

		if (distBetweenCentersSqr <= radiiSqr) {
			result = true;
		}
		return result;

	}

	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		
	}
}
