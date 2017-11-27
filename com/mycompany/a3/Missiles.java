package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class Missiles extends MovObjects implements ISelectable {
	private double fuel;
	private boolean isSelected;

	// Missile constructor, set the color, speed, direction, and location
	public Missiles(Ship s) {
		setSize(s.getSize() + 3);
		setColor(0, 255, 255);
		setSpeed(s.getSpeed() + 30);
		setDirection(s.getDirection());
		setLocation(s.getLocX(), s.getLocY() - getSize() * 2);
		fuel = 40;
	}

	// get current fuel level
	public double getFuel() {
		return fuel;
	}

	// set current fuel level
	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	// use a level of fuel
	public void useFuel() {
		setFuel(getFuel() - 1);
	}
	//draw a missile
	public void draw(Graphics g, Point relsPoint) {
		int xCenter = (int) relsPoint.getX() + (int) getLocX() - getSize();
		int yCenter = (int) relsPoint.getY() + (int) getLocY() - getSize();
		g.setColor(this.getColor());
		if (this.isSelected()) {
			g.drawArc(xCenter + getSize(), yCenter + getSize(), getSize() / 2, getSize(), 0, 360);
		} else {
			g.fillArc(xCenter + getSize(), yCenter + getSize(), getSize() / 2, getSize(), 0, 360);
		}
	}

	// print out the fuel level and parent toString()
	public String toString() {
		return "Missile: " + super.toString() + ", fuel:" + this.getFuel();
	}

	public void move() {
	}

	public void setSelected(boolean yesNo) {
		isSelected = yesNo;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int x = (int) pPtrRelPrnt.getX();
		int y = (int) pPtrRelPrnt.getY();
		int xLoc = (int) pCmpRelPrnt.getX() + (int) getLocX();
		int yLoc = (int) pCmpRelPrnt.getY() + (int) getLocY();
		if ((x >= xLoc) && (x <= xLoc + getSize()) && (y >= yLoc) && (y <= yLoc + getSize())) {
			return true;
		} else {
			return false;
		}

	}

	public void setLocation(int x, int y) {
		this.setLocation((double) x, (double) y);
	}

	public boolean collidesWith(GameObject otherObject) {

		boolean result = false;

		int thisCenterX = (int) (this.getLocX() + (this.getSize()/2)); // find
																			// centers
		int thisCenterY = (int) (this.getLocY() + (this.getSize() / 2));

		int otherCenterX = (int) (((GameObject) otherObject).getLocX() + (this.getSize() / 2));
		int otherCenterY = (int) (((GameObject) otherObject).getLocY() + (this.getSize() / 2));

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
}
