package com.mycompany.a3;

public abstract class FixedObjects extends GameObject {
	private static int idNum = 0;
	private int id = 0;
	//FixedObject constructor, sets id and makes it increment for new objects
	public FixedObjects(){
		id = idNum++;
	}
	//print out ID + parent toString()
	public String toString(){
		return super.toString() + ", id:" + id;
	}
}
