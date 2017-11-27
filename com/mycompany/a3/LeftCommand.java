package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.*;

public class LeftCommand extends Command{
private static GameWorld target;

	
	public LeftCommand(GameWorld gw){
		super("Left");
		target = gw;
	}
	
	public static void setTarget(GameWorld tgw){
		if(target == null){
			target = tgw;
		}
	}
	
	public void actionPerformed(ActionEvent e){
		target.turnLeft(); 
	}
}
