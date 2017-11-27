package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class Ship extends MovObjects implements ISteerable, ICollide {
	private int missileCount;

	// Ship constructor, sets the color, missile count, speed, direction, and
	// location
	public Ship(int locX, int locY) {
		setSize(10);
		setColor(255, 0, 255);
		missileCount = 10;
		setSpeed(0);
		setDirection(180);
		setLocation(locX / 2, locY / 2);
	}
	//draw a ship
	public void draw(Graphics g, Point relsPoint) {
		int centerX = (int) relsPoint.getX() + (int) getLocX();
		int centerY = (int) relsPoint.getY() + (int) getLocY();

		g.setColor(this.getColor());
		g.fillTriangle(centerX, centerY - getSize(), centerX + getSize(), centerY + getSize(), centerX - getSize(),
				centerY + getSize());
	}

	// decrement missile count from shot
	public void missileShot() {
		missileCount--;
	}

	// get the missile count
	public int getMissiles() {
		return missileCount;
	}

	// reset the missile
	public void resetMissiles() {
		missileCount = 10;
	}

	// decrease speed
	public void decSpeed() {
		if (getSpeed() == 0) {
			System.out.println("Already at minimum speed.");
			return;
		}
		int speedDec = getSpeed() - 2;
		setSpeed(speedDec);
	}

	// increase speed
	public void incSpeed() {
		if (getSpeed() == 10) {
			System.out.println("Already at maximum speed.");
			return;
		}
		int speedInc = getSpeed() + 2;
		setSpeed(speedInc);
	}

	// turn the object left
	public void turnLeft() {
		if (getDirection() <= 340) {
			setDirection(getDirection() + 20);
		} else if (getDirection() > 340) {
			setDirection(0);
		}
	}

	// turn the object right
	public void turnRight() {
		if (getDirection() > 0) {
			setDirection(getDirection() - 20);
		} else if (getDirection() == 0) {
			setDirection(340);
		}

	}

	// prints out missile count + parent toString()
	public String toString() {
		return "Ship: " + super.toString() + ", Missiles:" + getMissiles();
	}

	public void move() {
		// TODO Auto-generated method stub

	}

	public boolean collidesWith(GameObject otherObject) {

		boolean result = false;

		int thisCenterX = (int) (this.getLocX() + (this.getSize() / 2)); // find
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
		int thisRadius = this.getSize()/2;
		int otherRadius = otherObject.getSize()/2;
		int radiiSqr = thisRadius + otherRadius;

		if (distBetweenCentersSqr <= radiiSqr) {
			result = true;
		}
		return result;

	}

	public void handleCollision(GameObject otherObject) {
		missileCount = 10;
	}
}