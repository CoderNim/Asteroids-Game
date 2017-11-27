package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.*;

public class FireCommand extends Command{
private static GameWorld target;

	
	public FireCommand(GameWorld gw){
		super("Fire");
		target = gw;
	}
	
	public static void setTarget(GameWorld tgw){
		if(target == null){
			target = tgw;
		}
	}
	
	public void actionPerformed(ActionEvent e){
		target.fireMissile(); 
	}
}
