package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.models.Point;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	private Game game;

	public MapView(GameWorld gw, Game game) {
		this.gw = gw;
		this.game = game;
	}

	// When an observable object is passed in, the map view prints a map of the
	// game to the console
	public void update(Observable o, Object args) {
		if (o instanceof GameWorld) {
			((GameWorld) o).printTextMap();
			repaint();
		}
	}
	//paint objets in the mapview container
	public void paint(Graphics g) {
		super.paint(g);
		Point relPoint = new Point(getX(), getY());
		IIterator iterator = gw.getCollection().getIterator();
		while (iterator.hasNext()) {
			GameObject currentObject = (GameObject) iterator.next();
			currentObject.draw(g, relPoint);
		}
	}
	//selects items that are selectable
	@Override
	public void pointerPressed(int x, int y) {
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		IIterator iterator = gw.getCollection().getIterator(); 
		if(!game.getState()){
			while (iterator.hasNext()){
				Object curObject = iterator.next();
				if(curObject instanceof ISelectable){
					ISelectable selectable = (ISelectable) curObject;
					if(selectable.contains(pPtrRelPrnt, pCmpRelPrnt)){
						selectable.setSelected(true);
					}
					else{
						selectable.setSelected(false);
					}
				}
			}
		}
		else{
			iterator = gw.getCollection().getIterator();
			while(iterator.hasNext()){
				Object curObject = iterator.next();
				if(curObject instanceof ISelectable){
					ISelectable selectable = (ISelectable) curObject;
					selectable.setSelected(false);
				}
			}
		}
		repaint();
	}
	

}
