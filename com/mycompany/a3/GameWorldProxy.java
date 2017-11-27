package com.mycompany.a3;
import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameworld{
	private GameWorld target;
	public GameWorldProxy(GameWorld gw){
		target = gw;
		this.setChanged();
	}

	public int getScore() {
		return target.getScore();
	}

	public int getMissile() {
		return target.getMissile();
	}

	public int getTime() {
		return target.getTime();
	}

	public boolean getSound() {
		return target.getSound();
	}

}