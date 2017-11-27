package com.mycompany.a3;
import java.util.Observer;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;


import java.util.Observable;
import com.codename1.charts.util.ColorUtil;

public class PointsView extends Container implements Observer{

	private Label score;
	private Label time;
	private Label missiles;
	private Label sound;
	private Label getScore;
	private Label getTime;
	private Label getMissile;
	private Label getSound;
	
	
	public PointsView(){
		//Create labels to display the score, time, missiles left, and sound
		
		score = new Label("Score: ");
		time = new Label("Time: ");
		missiles = new Label("Missiles: ");
		sound = new Label("Sound: ");
		getScore = new Label("00");
		getTime = new Label("0000");
		getMissile = new Label("00"); 
		getSound = new Label("Off");
		getScore.getAllStyles().setPadding(2, 2, 2, 2);
		getTime.getAllStyles().setPadding(2, 2, 2, 2);
		getMissile.getAllStyles().setPadding(2, 2, 2, 2);
		getSound.getAllStyles().setPadding(2, 2, 2, 2);
		score.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		time.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		missiles.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		sound.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		this.add(score);
		this.add(getScore);
		this.add(time);
		this.add(getTime);
		this.add(missiles);
		this.add(getMissile);
		this.add(sound);
		this.add(getSound);
		
		
	}
	public void update(Observable o, Object arg){
		//Set the text in the container to display the current attributes of the game
		getTime.setText(Integer.toString(((IGameworld)o).getTime()));
		getScore.setText(Integer.toString(((IGameworld)o).getScore()));
		getMissile.setText(Integer.toString(((IGameworld)o).getMissile()));
		if(((IGameworld)o).getSound() == false){
			getSound.setText("Off");
		}
		if(((IGameworld)o).getSound() == true){
			getSound.setText("On");
		}
	}
}
