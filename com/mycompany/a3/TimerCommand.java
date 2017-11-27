package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TimerCommand extends Command{
	private Game game;
	public TimerCommand(Game game){
		super("Pause");
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if (getCommandName() == "Pause") {
			super.setCommandName("Play");
			game.pause();
		}
		else {
			game.play();
			super.setCommandName("Pause");
		}
	}
}

