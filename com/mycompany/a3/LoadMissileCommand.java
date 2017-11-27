package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.*;

public class LoadMissileCommand extends Command{
private static GameWorld target;

	
	public LoadMissileCommand(GameWorld gw){
		super("Load Missile");
		target = gw;
	}
	
	public static void setTarget(GameWorld tgw){
		if(target == null){
			target = tgw;
		}
	}
	
	public void actionPerformed(ActionEvent e){
		target.loadMissiles(); 
	}
}
