package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class RefuelCommand extends Command{
	private static GameWorld target;
	public RefuelCommand(GameWorld gw){
		super("Refuel");
		target = gw;
	}
	
	public static void setTarget(GameWorld tgw){
		if(target == null){
			target = tgw;
		}
	}
	
	public void actionPerformed(ActionEvent e){
		target.refuel(); 
	}
}
