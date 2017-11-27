package com.mycompany.a3;

import java.util.Observable;
import com.codename1.media.Media;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;

import java.util.*;

public class GameWorld extends Observable implements IGameworld {
	private int score;
	private int gameClock;
	private int lives;
	private int width;
	private int height;
	private int asteroidCount;
	private int curMissileCount;
	private Ship ship;
	private boolean existingShip;
	private boolean existingStation;
	private static boolean sound;
	private boolean soundPause;

	private Sound fireShots = new Sound("missile.wav");
	private BGSound theme = new BGSound("retro.wav");
	private Sound collision = new Sound("blast.wav");
	private Sound gameOver = new Sound("gameover.WAV");
	private ObjectCollection gameCollection;

	// initialize the GameWorld
	public void init(int height, int width) {
		this.height = height;
		this.width = width;
		gameCollection = new ObjectCollection();
		sound = false;
		score = 0;
		gameClock = 0;
		lives = 3;
		existingShip = false;
		existingStation = false;
		soundPause = true;
	}

	// Set the game width to match the layout
	public void setWidth(int layoutX) {
		width = layoutX;
	}

	// get the container width
	public int getWidth() {
		return width;
	}

	// Set the game height of mapview
	public void setHeight(int layoutY) {
		height = layoutY;
	}
	// return height of the mapview
	public int getHeight() {
		return height;
	}
	// flag for sound when the game is paused
	public boolean getSoundPause(){
		return soundPause;
	}
	//flag for sound
	public void setSoundPause(boolean flag){
		soundPause = flag;
	}
	// return the game object collection
	public ObjectCollection getCollection() {
		return gameCollection;
	}
	// set the value of the existing ship
	public void setExistingShip(boolean flag){
		existingShip = flag;
	}
	//get the boolean for existing ship
	public boolean getExistingShip(){
		return existingShip;
	}
	// print a text map of the game world
	public void printTextMap() {
		IIterator iterator = gameCollection.getIterator();
		System.out.println("***********************************");
		System.out.println("Width: " + width + " Height: " + height);
		System.out.println("Current Map: ");
		while (iterator.hasNext()) {
			GameObject currentObject = (GameObject) iterator.next();
			System.out.println(currentObject.toString());
		}
	}

	/*
	 * ScoreView Values
	 */

	// get the game clock
	public int getTime() {
		return gameClock;
	}

	// get the game score
	public int getScore() {
		return score;
	}
	//get current sound state
	public boolean getSound() {
		return sound;
	}
	//toggle the sound state
	public void toggleSound() {
		if(getSoundPause()){
			if (getSound() == false) {
				setSound(true);
				System.out.println("Sound is on.");
				theme.play();
			} else if (getSound() == true) {
				setSound(false);
				System.out.println("Sound is off.");
				theme.pause();
			}
			notifyGame();
		}
	}
	// manually set the sound
	public void setSound(boolean newSound) {
		sound = newSound;
	}

	// get the current missiles left
	public int getMissile() {
		if (existingShip == false) {
			return 0;
		}
		return ship.getMissiles();
	}

	// increment game clock
	public void incrTime() {
		gameClock++;
	}

	// decrement lives
	public void decLives() {
		lives--;
	}
	// get the current lives
	public int getLives() {
		return lives;
	}
	// refuel a missile
	public void refuel(){
		IIterator iterator = gameCollection.getIterator();
		// remove all objects in the game collection
		while (iterator.hasNext()) {
			GameObject currentObject = iterator.next();
			if(currentObject instanceof Missiles){
				currentObject = (Missiles) currentObject;
				if(((ISelectable) currentObject).isSelected()){
					((Missiles) currentObject).setFuel(40);
				}
			}
		}
	}
	// Reset the game with the original values, take all objects out of
	// Collection
	public void gameReset() {
		if(getSound() == true){
			gameOver.play();
		}
		IIterator iterator = gameCollection.getIterator();
		// remove all objects in the game collection
		while (iterator.hasNext()) {
			GameObject currentObject = (GameObject) iterator.next();
			gameCollection.remove(currentObject);
			iterator.reduceIndex();
		}
		gameClock = 0;
		score = 0;
		lives = 3;
		existingShip = false;
	}
	/*
	 * All the buttons and game commands
	 */

	// add a new asteroid to the world
	public void addAsteroid() {
		asteroidCount++;
		Asteroid asteroid = new Asteroid(width, height);
		gameCollection.add(asteroid);
		notifyGame();
	}

	// add new ship
	public void addShip() {
		if (getExistingShip() == true) {
			System.out.println("There is already a ship, cannot have another.");
			return;
		}
		ship = new Ship(width, height);
		setExistingShip(true);
		gameCollection.add(ship);
		notifyGame();
	}

	// add new space station
	public void addStation() {
		SpaceStation station = new SpaceStation(width, height);
		gameCollection.add(station);
		existingStation = true;
		notifyGame();
	}

	// increase speed of ship
	public void incSpeedShip() {
		if (existingShip == true) {
			ship.incSpeed();
		} else {
			System.out.println("No ship exists, cannot increase speed.");
		}
		notifyGame();
	}

	// decrease speed of ship
	public void decSpeedShip() {
		if (existingShip == true) {
			ship.decSpeed();
		} else {
			System.out.println("No ship exists, cannot decrease speed.");
		}
		notifyGame();
	}

	// turn left by small amount
	public void turnLeft() {
		if (existingShip == true) {
			ship.turnLeft();
		} else {
			System.out.println("No ship exists, cannot turn left.");
		}
		notifyGame();
	}

	// turn right by small amount
	public void turnRight() {
		if (existingShip == true) {
			ship.turnRight();
		} else {
			System.out.println("No ship exists, cannot turn right.");
		}
		notifyGame();
	}

	// fire a missile
	public void fireMissile() {
		if (existingShip == true) {
			if(getSound() == true){
				fireShots.play();
			}
			if (ship.getMissiles() == 0) {
				System.out.println("Out of missiles");
				return;
			}
			Missiles missile = new Missiles(ship);
			gameCollection.add(missile);
			curMissileCount++;
			ship.missileShot();
		} else {
			System.out.println("Cannot fire missile, no ship exists");
		}
		notifyGame();
	}

	// jump through middle of screen
	public void hyperSpace() {
		if (existingShip == true) {
			ship.setLocation(Math.round(width / 2), Math.round(height / 2));
		} else {
			System.out.println("No Ship exists.");
		}
		notifyGame();
	}

	// load new supply of missiles to ship, only if there a spaceship
	public void loadMissiles() {
		if (existingStation == true) {
			ship.resetMissiles();
			return;
		} else {
			System.out.println("Cannot reload missiles, no space station exists.");
		}
		notifyGame();
	}

	// Remove Asteroid and missile for colliding, increment score
	

	// Game clock has ticked, all moveable objects are told to update
	// positions, fuel level is reduced, any missiles out of fuel are
	// removed from the game. Each space station toggles blinking light of
	// elapsed game time is incremented by one
	public void tick(int time) {
		incrTime();
		IIterator iterator = gameCollection.getIterator();
		// Tell all movable objects to move
		while (iterator.hasNext()) {
			GameObject currentObject = (GameObject) iterator.next();
			if (currentObject instanceof MovObjects) {
				((MovObjects) currentObject).move(width, height, time);
			}
		}
		// Use missile fuel, remove missiles that are out of fuel
		iterator = gameCollection.getIterator();
		while (iterator.hasNext()) {
			GameObject currentObject = (GameObject) iterator.next();
			if (currentObject instanceof Missiles) {
				((Missiles) currentObject).useFuel();
				if (((Missiles) currentObject).getFuel() == 0) {
					curMissileCount--;
					gameCollection.remove(currentObject);
					iterator = gameCollection.getIterator();
				}
			}
		}

		// Toggle the spaceship light
		int timeLight = getTime();
		iterator = gameCollection.getIterator();
		while (iterator.hasNext()) {
			GameObject currentObject = (GameObject) iterator.next();
			if (currentObject instanceof SpaceStation) {
				((SpaceStation) currentObject).toggleLight(timeLight);
			}
		}
		//check object collisions
		iterator = gameCollection.getIterator();
		while (iterator.hasNext()) {
			// GameObject curObj = (GameObject) iterator.next();
			if (iterator.next() instanceof GameObject) {
				GameObject currentObject = iterator.getCurrent();
				IIterator otherObjects = gameCollection.getIterator();
				while (otherObjects.hasNext()) {
					GameObject collideObject = otherObjects.next();
					if (currentObject != collideObject) {
						if (currentObject instanceof Asteroid) {
							if (collideObject instanceof Asteroid) {
								if (((Asteroid) currentObject).collidesWith(collideObject)) {
									iterator.remove((GameObject) currentObject);
									asteroidCount--;
									otherObjects.remove(collideObject);
									asteroidCount--;
									break;

								}
							}
							else if (collideObject instanceof Ship) {
								if (((Asteroid) currentObject).collidesWith(collideObject)) {
									iterator.remove(currentObject);
									otherObjects.remove(collideObject);
									setExistingShip(false);
									addShip();
									decLives();
									asteroidCount--;
									if (getLives() == 0) {
										System.out.println("Game over.");
										System.out.println("Resetting game");
										gameReset();
									}
									System.out.println(getExistingShip());
									break;
								}
							}
							else if (collideObject instanceof Missiles) {
								if (((Asteroid) currentObject).collidesWith(collideObject)) {
									iterator.remove((GameObject) currentObject);
									otherObjects.remove(collideObject);
									System.out.println("There was a collision");
									if(getSound() == true){
										collision.play();
									}
									curMissileCount--;
									asteroidCount--;
									score++;
									break;
								}
							}
						}
					
						if (currentObject instanceof Ship) {
							if (((Ship) currentObject).collidesWith(collideObject)) {
								if (collideObject instanceof Asteroid) {
									iterator.remove(currentObject);
									otherObjects.remove(collideObject);
									setExistingShip(false);
									addShip();
									decLives();
									if (getLives() == 0) {
										System.out.println("Game over.");
										System.out.println("Resetting game");
										gameReset();
									}
									System.out.println(getExistingShip());
									break;
								}
							}
							else if (collideObject instanceof SpaceStation) {
								if (((Ship) currentObject).collidesWith(collideObject)) {
									((Ship) currentObject).handleCollision(collideObject);
									break;
								}
							}
						}
						if (currentObject instanceof Missiles) {
							if (((Missiles) currentObject).collidesWith(collideObject)) {
								if (collideObject instanceof Asteroid) {
									iterator.remove((GameObject) currentObject);
									otherObjects.remove(collideObject);
									System.out.println("There was a collision");
									if(getSound() == true){
										collision.play();
									}
									curMissileCount--;
									asteroidCount--;
									score++;
									break;
								}
							}
						}
						if (currentObject instanceof SpaceStation) {
							if (((SpaceStation) currentObject).collidesWith(collideObject)) {
								if (collideObject instanceof Ship) {
									((Ship) collideObject).handleCollision(currentObject);
									break;
								}
							}
						}
					}
				}
			}
		}
		notifyGame();

	}

	// quit by calling system.exit(0), your program should
	public void quit() {
		if (Dialog.show("Are you sure you want to quit?", "Choose: ", "Yes", "No")) {
			System.exit(0);
		} else {
			return;
		}
	}

	public void about() {
		Dialog.show("Nima Sarrafzadeh", "CSC 133 Asteroid Game", "Cancel", "Okay");
	}

	// Notify the observer that something in gameworld has changed
	public void notifyGame() {
		GameWorldProxy prx = new GameWorldProxy(this);
		this.setChanged();
		this.notifyObservers(prx);

	}

}