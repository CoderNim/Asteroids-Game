package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.io.Log;
import java.lang.String;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.*;
import com.codename1.ui.events.*;

public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	private UITimer timer;
	private int height;
	private int width;
	private Button timerPause;
	private Button right;
	private Button left;
	private Button increase;
	private Button decrease;
	private Button fire;
	private Button refuel;
	private CheckBox soundCheck;
	private Command increaseCommand;
	private Command decreaseCommand;
	private Command leftCommand;
	private Command rightCommand;
	private Command fireCommand;
	private Command soundCommand;
	private final int TIME = 150;
	boolean gameState;

	public Game() {
		gw = new GameWorld();
		mv = new MapView(gw, this);
		pv = new PointsView();
		gw.addObserver(mv);
		gw.addObserver(pv);
		timer = new UITimer(this);
		gameState = true;

		/*
		 * Code for a form with containers in different layout arrangements
		 *********************************************************************************
		 */

		setLayout(new BorderLayout());

		/// Add a toolbar
		Toolbar myToolbar = new Toolbar();
		myToolbar.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		setToolbar(myToolbar);
		myToolbar.setTitle("CSC 133");

		// top Container with the GridLayout positioned on the north
		pv.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLUE));
		add(BorderLayout.NORTH, pv);

		// left Container with the BoxLayout positioned on the west
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setPadding(Component.TOP, 5);
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLUE));
		add(BorderLayout.WEST, leftContainer);

		// right container with the gridLayout positioned in the east
		Container centerContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		mv.getAllStyles().setBorder(Border.createLineBorder(0, ColorUtil.BLUE));
		add(BorderLayout.CENTER, mv);

		Container bottomContainer = new Container(new FlowLayout(Component.CENTER));
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(0, ColorUtil.BLUE));
		add(BorderLayout.SOUTH, bottomContainer);

		/*
		 * Set of buttons and key listeners for U
		 *****************************************************************************
		 */

		// add button for adding an asteroid, set the 'a' key to invoke command
		Button asteroid = new Button("Add Asteroid");
		leftContainer.add(asteroid);
		AsteroidCommand asteroidCommand = new AsteroidCommand(gw);
		asteroid.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLUE));
		asteroid.getAllStyles().setPadding(2, 2, 2, 2);
		asteroid.setCommand(asteroidCommand);
		addKeyListener('a', asteroidCommand);

		// add button for ship, set the 's' key to invoke the command
		Button ship = new Button("Add Ship");
		leftContainer.add(ship);
		ShipCommand shipCommand = new ShipCommand(gw);
		ship.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLUE));
		ship.getAllStyles().setPadding(2, 2, 2, 2);
		ship.setCommand(shipCommand);
		addKeyListener('s', shipCommand);

		// add button for station, set the 'b' key to invoke the command
		Button station = new Button("Add Station");
		leftContainer.add(station);
		StationCommand stationCommand = new StationCommand(gw);
		station.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLUE));
		station.getAllStyles().setPadding(2, 2, 2, 2);
		station.setCommand(stationCommand);
		addKeyListener('b', stationCommand);

		// add button for increasing speed, set the 'i' key to invoke the
		// command
		increase = new Button("Increase");
		leftContainer.add(increase);
		increaseCommand = new IncreaseCommand(gw);
		increase.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLUE));
		increase.getAllStyles().setPadding(2, 2, 2, 2);
		increase.setCommand(increaseCommand);
		addKeyListener('i', increaseCommand);

		// add button for decreasing speed, set the 'd' key to invoke the
		// command
		decrease = new Button("Decrease");
		leftContainer.add(decrease);
		decreaseCommand = new DecreaseCommand(gw);
		decrease.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLUE));
		decrease.getAllStyles().setPadding(2, 2, 2, 2);
		decrease.setCommand(decreaseCommand);
		addKeyListener('d', decreaseCommand);

		// add button for turning left, set the 'l' key to invoke the command
		left = new Button("Left");
		leftContainer.add(left);
		leftCommand = new LeftCommand(gw);
		left.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLUE));
		left.getAllStyles().setPadding(2, 2, 2, 2);
		left.setCommand(leftCommand);
		addKeyListener('l', leftCommand);

		// add button for turning right, set the 'r' key to invoke the command
		right = new Button("Right");
		leftContainer.add(right);
		rightCommand = new RightCommand(gw);
		right.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLUE));
		right.getAllStyles().setPadding(2, 2, 2, 2);
		right.setCommand(rightCommand);
		addKeyListener('r', rightCommand);

		// add button for firingm set the 'f' key to invoke the command
		fire = new Button("Fire");
		leftContainer.add(fire);
		fireCommand = new FireCommand(gw);
		fire.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLUE));
		fire.getAllStyles().setPadding(2, 2, 2, 2);
		fire.setCommand(fireCommand);
		addKeyListener('f', fireCommand);

		// add button for jump, set the 'j' key to invoke the command
		Button jump = new Button("Jump");
		leftContainer.add(jump);
		JumpCommand jumpCommand = new JumpCommand(gw);
		jump.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLUE));
		jump.getAllStyles().setPadding(2, 2, 2, 2);
		jump.setCommand(jumpCommand);
		addKeyListener('j', jumpCommand);

		// add button for game tick, set the 't' key to invoke the command
		/*
		 * Button tick = new Button("Tick"); leftContainer.add(tick);
		 * TickCommand tickCommand = new TickCommand(gw);
		 * tick.getAllStyles().setBorder(Border.createLineBorder(3,
		 * ColorUtil.BLUE)); tick.getAllStyles().setPadding(2, 2, 2, 2);
		 * tick.setCommand(tickCommand); addKeyListener('t', tickCommand);
		 */

		// add button for quitting, set the 'q' key to invoke the command
		Button quit = new Button("Quit");
		leftContainer.add(quit);
		QuitCommand quitCommand = new QuitCommand(gw);
		quit.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLUE));
		quit.getAllStyles().setPadding(2, 2, 2, 2);
		quit.setCommand(quitCommand);
		addKeyListener('q', quitCommand);

		//add button to pause and play the game
		timerPause = new Button("Pause");
		leftContainer.add(timerPause);
		timerPause.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLUE));
		timerPause.getAllStyles().setPadding(2, 2, 2, 2);
		TimerCommand timerCommand = new TimerCommand(this);
		timerPause.setCommand(timerCommand);
		addKeyListener('t', timerCommand);

		//add button to refuel missiles
		refuel = new Button("Refuel");
		leftContainer.add(refuel);
		refuel.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLUE));
		refuel.getAllStyles().setPadding(2, 2, 2, 2);
		RefuelCommand refuelCommand = new RefuelCommand(gw);
		refuel.setCommand(refuelCommand);
		refuel.setEnabled(false);

		/*
		 * The following buttons and commands are added to the toolbar (overflow
		 * and sidemenu)
		 ************************************************************************************
		 */

		// Set the following commands to the overflow menu
		Command newB = new Command("New");
		Command save = new Command("Save");
		Command undo = new Command("Undo");
		AboutCommand aboutCommand = new AboutCommand(gw);
		myToolbar.addCommandToOverflowMenu(newB);
		myToolbar.addCommandToOverflowMenu(save);
		myToolbar.addCommandToOverflowMenu(aboutCommand);
		myToolbar.addCommandToOverflowMenu(quitCommand);
		myToolbar.addCommandToOverflowMenu(undo);

		// Create a checkbox for the sound option
		soundCheck = new CheckBox("		Sound");
		soundCommand = new SoundCommand(gw);
		soundCheck.getAllStyles().setBgTransparency(255);
		soundCheck.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundCheck.setCommand(soundCommand);
		soundCommand.putClientProperty("Sound", soundCheck);
		myToolbar.addComponentToSideMenu(soundCheck);

		// Timer
		timer.schedule(50, true, this);

		this.show();

		// obtain the dimensions of the mapview and set the values in gameworld
		height = mv.getLayoutHeight();
		width = mv.getLayoutWidth();
		gw.init(height, width);

	}
	//run the game
	public void run() {
		gw.tick(TIME);
	}
	
	//pause gameplay
	public void pause() {

		increase.setEnabled(false);
		removeKeyListener('i', increaseCommand);
		decrease.setEnabled(false);
		removeKeyListener('d', decreaseCommand);
		left.setEnabled(false);
		removeKeyListener('l', leftCommand);
		right.setEnabled(false);
		removeKeyListener('r', rightCommand);
		fire.setEnabled(false);
		removeKeyListener('f', fireCommand);
		timer.cancel();
		gameState = false;
		refuel.setEnabled(true);
		if (gw.getSound()){
			gw.toggleSound();
			//soundCheck.setEnabled(false);
		}
		gw.setSoundPause(false);
	timerPause.setText("Play");

	}
	// resume gameplay
	public void play() {
		mv.pointerPressed(-1, -1);
		refuel.setEnabled(false);
		increase.setEnabled(true);
		addKeyListener('i', increaseCommand);
		decrease.setEnabled(true);
		addKeyListener('d', decreaseCommand);
		left.setEnabled(true);
		addKeyListener('l', leftCommand);
		right.setEnabled(true);
		addKeyListener('r', rightCommand);
		fire.setEnabled(true);
		addKeyListener('f', fireCommand);
		timer.schedule(TIME, true, this);
		gameState = true;
		gw.setSoundPause(true);
		if (soundCheck.isSelected())
			gw.toggleSound();
		timerPause.setText("Pause");
	}

	public boolean getState() {
		return gameState;
	}

}
