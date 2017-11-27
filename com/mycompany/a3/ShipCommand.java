package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.*;


public class ShipCommand extends Command{
	private static GameWorld target;

	
	public ShipCommand(GameWorld gw){
		super("Ship");
		target = gw;
	}
	
	public static void setTarget(GameWorld tgw){
		if(target == null){
			target = tgw;
		}
	}
	
	public void actionPerformed(ActionEvent e){
		target.addShip(); 
	}
}
