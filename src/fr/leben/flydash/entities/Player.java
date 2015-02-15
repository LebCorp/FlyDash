package fr.leben.flydash.entities;

import org.lwjgl.input.Keyboard;

import fr.leben.flydash.graphics.Color;
import fr.leben.flydash.graphics.Image;
import fr.leben.flydash.graphics.Renderer;
import fr.leben.flydash.graphics.particles.Particle;
import fr.leben.flydash.graphics.particles.ParticleSystem;
import fr.leben.flydash.math.Vector2f;
import fr.leben.flydash.renderlevel.Level;
import fr.leben.flydash.utils.Animation;

public class Player extends Entity{

	Animation anim;
	int dir = 0;

	public Player(int x, int y) {
		super(x, y);
		texture = Image.player;
		anim = new Animation(3, 10, true);
		mass = 0.1f;
		drag = 0.95f;
	}

	float xa, ya;
	float speed = 0.1f;

	public void update() {
		ya += level.gravity * mass;
		if (isGrounded()) {
			drag = 0.85f;
		} else {
			drag = 0.95f;
		}

		anim.update();
		anim.pause();

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			ya -= 0.3;
			Particle particle = new Particle(Color.GREEN, new Vector2f(0, 4), 10, 0.5f, 10);
			level.addEntity(new ParticleSystem((int) x + 8 - 5, (int) y + 8, 2, particle));
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_Z) || Keyboard.isKeyDown(Keyboard.KEY_W)) {
			if (isGrounded()) {
				ya -= 3.8;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q) || Keyboard.isKeyDown(Keyboard.KEY_A)) {
			xa -= speed;
			dir = 1;
			anim.play();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			xa += speed;
			dir = 0;
			anim.play();
		}

		int xStep = (int) Math.abs(xa * 100);
		for (int i = 0; i < xStep; i++) {
			if (!isSolidTile(xa / xStep, 0)) {
				x += xa / xStep;
			} else {
				xa = 0;
			}
		}
		int yStep = (int) Math.abs(ya * 100);
		for (int i = 0; i < yStep; i++) {
			if (!isSolidTile(0, ya / yStep)) {
				y += ya / yStep;
			} else {
				ya = 0;
			}
		}

		xa *= drag;
		ya *= drag;
	}

	public void render() {
		texture.bind();
		Renderer.renderEntity(x, y, 16, 16, Color.WHITE, 4, 1 + dir, anim.getCurrentFrame());
		texture.unbind();
	}

	public void init(Level level) {
		this.level = level;
	}
}
