
import java.io.IOException;

import javafx.scene.image.Image;

public class Projectile extends GameObject {
	Direction direction;

	Projectile(Direction d, double x, double y) {
		this(d, x, y, false);
	}

	Projectile(Direction d, double x, double y, boolean isEnemy) {
		super(new Image("sprites/spr_bullet.png"));
		this.sprite = Main.resizeImage(getSprite(), (int) Main.SCREEN_WIDTH / 50, (int) Main.SCREEN_WIDTH / 120);
		setX(x);
		setY(y);
		direction = d;
		speed = 10;
		setHitboxVisible(true);
		this.isEnemy = true;
	}
	
	@Override
	void update() {
		switch (direction) {
		case UP:
			setY(getY() - 1 * speed);
			break;
		case DOWN:
			setY(getY() + 1 * speed);
			break;
		case LEFT:
			setX(getX() - 1 * speed);
			break;
		case RIGHT:
			setX(getX() + 1 * speed);
			break;
		}
		
		if (isOffScreen())
			this.isDead = true;
	}
	
	@Override
	public void destroy() {
		isDead = true;
	}

	@Override
	public String toString() {
		return "Projectile direction:" + direction + " enemy:" + isEnemy;
	}

}
