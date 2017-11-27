package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class SoundCommand extends Command{
	private static GameWorld target;
	
	public SoundCommand(GameWorld gw){
		super("Sound");
		target = gw;
	}
	public static void setTarget(GameWorld tgw){
		if(target == null){
			target = tgw;
		}
	}
	public void actionPerformed(ActionEvent e){
		target.toggleSound();
	}
}
