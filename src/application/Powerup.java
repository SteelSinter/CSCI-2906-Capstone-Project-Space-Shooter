package application;

import javafx.scene.image.Image;

public class Powerup extends GameObject {
	public static enum PowerupType {SPEED, FIRERATE, GUN, FRIEND}
	
	Powerup() {
		super(new Image("sprites/spr_powerup"));
		setHitboxVisible(true);
		speed = 0.5;
	}
	
	@Override
	void update() {
		// TODO Auto-generated method stub

	}

	@Override
	void destroy() {
		isDead = true;

	}
	
	void collect() {
		System.out.println("Powerup collected");
	}

}
