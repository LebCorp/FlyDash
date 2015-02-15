package fr.leben.flydash.graphics.particles;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import fr.leben.flydash.entities.Entity;
import fr.leben.flydash.renderlevel.Level;

public class ParticleSystem extends Entity {

	private List<Particle> particles = new ArrayList<Particle>();

	public ParticleSystem(int x, int y, int num, Particle p) {
		super(x, y);
		for (int i = 0; i < num; i++) {
			particles.add(new Particle(p, x, y));
		}
	}

	public void init(Level level) {
		this.level = level;
	}

	public void update() {
		for (int i = 0; i < particles.size(); i++) {
			Particle p = particles.get(i);
			if (p.removed)
				particles.remove(p);
			p.update();
		}
	}

	public void render() {
		glBegin(GL_QUADS);
		for (Particle p : particles) {
			p.render();
		}
		glEnd();
	}
}
