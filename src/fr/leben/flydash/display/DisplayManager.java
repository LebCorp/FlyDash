package fr.leben.flydash.display;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class DisplayManager {

	/** Create the display*/
	
	public static void create(int width, int height, String title) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	/** Update the display*/
	public static void update(){
		while(!Display.isCloseRequested()){
			Display.update();
		}
		
		Display.destroy();
	}
}
