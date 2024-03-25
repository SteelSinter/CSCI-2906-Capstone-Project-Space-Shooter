
import java.io.IOException;

import javafx.scene.image.Image;

public class Projectile extends GameObject {
	Direction direction;

	Projectile(Direction d, double x, double y) {
		super(new Image("sprites/spr_bullet.png"));
		this.sprite = Main.resizeImage(getSprite(), (int) Main.SCREEN_WIDTH / 50, (int) Main.SCREEN_WIDTH / 100);
		setX(x);
		setY(y);
		direction = d;
		speed = 10;
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
