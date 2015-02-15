package fr.leben.flydash;

import fr.leben.flydash.display.DisplayManager;

public class FlyDash {

	/** Init the display*/
	
	public static void main(String[] args){
		DisplayManager.create(1280, 720, "Title");
		DisplayManager.update();
	}
}
