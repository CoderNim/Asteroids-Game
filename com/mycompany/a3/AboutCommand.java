package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.*;


public class AboutCommand extends Command{
	private static GameWorld target;

	
	public AboutCommand(GameWorld gw){
		super("About");
		target = gw;
	}
	
	public static void setTarget(GameWorld tgw){
		if(target == null){
			target = tgw;
		}
	}
	
	public void actionPerformed(ActionEvent e){
		target.about(); 
	}
}