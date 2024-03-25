

import java.io.IOException;

import javafx.scene.image.Image;

public class Powerup extends GameObject {
	public static enum PowerupType {SPEED, FIRERATE, GUN, FRIEND}
	
	Powerup() throws IOException {
		super(new Image("sprites/spr_powerup"));
		setHitboxVisible(true);
		speed = 0.5;
	}
	
	@Override
	void update() {
		setX(getX() - 1 * speed);

	}

	@Override
	void destroy() {
		isDead = true;

	}
	
	void collect() {
		System.out.println("Powerup collected");
	}

}
