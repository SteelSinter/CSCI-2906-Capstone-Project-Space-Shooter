package application;
import javafx.scene.image.Image;

public class Projectile extends GameObject {
	Direction direction;

	Projectile(Direction d, double x, double y) {
		super(new Image("sprites/spr_bullet.png"));
		setX(x);
		setY(y);
		direction = d;
		speed = 5;
		setHitboxVisible(true);
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
			game.removeObject(this);
	}
	
	@Override
	public void destroy() {
		isDead = true;
	}

}
