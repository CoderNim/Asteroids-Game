package com.mycompany.a3;


public interface ICollide {
	public boolean collidesWith(GameObject otherObject);
	public void handleCollision(GameObject otherObject);
}
