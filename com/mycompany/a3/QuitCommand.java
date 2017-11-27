package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.*;

public class QuitCommand extends Command{
private static GameWorld target;

	
	public QuitCommand(GameWorld gw){
		super("Quit");
		target = gw;
	}
	
	public static void setTarget(GameWorld tgw){
		if(target == null){
			target = tgw;
		}
	}
	
	public void actionPerformed(ActionEvent e){
		target.quit();
	}
}
