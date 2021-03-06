package fr.leben.flydash.entities;


import org.lwjgl.examples.spaceinvaders.Texture;

import fr.leben.flydash.graphics.Image;
import fr.leben.flydash.renderlevel.Level;

public abstract class Entity {

	protected float x, y;
	protected boolean removed = false;
	protected Image texture;
	protected Level level;
	protected float drag;
	protected float mass;
	
	public Entity(int x, int y) {
		this.x = x * 16;
		this.y = y * 16;
	}
	
	public abstract void init(Level level);
	public abstract void update();
	public abstract void render();
	
	public boolean isSolidTile(float xa, float ya) {
		
		int x0 = (int) (x + xa + 3) / 16;
		int x1 = (int) (x + xa + 13) / 16;
		int y0 = (int) (y + ya + 2) / 16;
		int y1 = (int) (y + ya + 14) / 16;
		
		if (level.getSolidTile(x0, y0) != null) return true;
		if (level.getSolidTile(x1, y0) != null) return true;
		if (level.getSolidTile(x1, y1) != null) return true;
		if (level.getSolidTile(x0, y1) != null) return true;
		
		return false;
	}
	
	public boolean isGrounded() {
		if (level.getSolidTile((int) (x + 5) / 16, (int) (y + 14.1) / 16) != null) return true;
		return false;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public boolean getRemoved() {
		return removed;
	}
	
	public Texture getTexture() {
		return texture;
	}
}
