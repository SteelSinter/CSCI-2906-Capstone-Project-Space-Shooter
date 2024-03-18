import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Projectile extends GameObject {
	Direction direction;

	Projectile(Direction d, double x, double y) {
		setX(x);
		setY(y);
		direction = d;
		setImage(new Image("sprites/spr_bullet.png"));
		speedMultiplier = 5;
	}
	
	@Override
	void update() {
		
		
	}

	@Override
	void draw() {
		switch (direction) {
		case UP:
			this.setY(this.getY() - 1 * speedMultiplier);
			break;
		case DOWN:
			this.setY(this.getY() + 1 * speedMultiplier);
			break;
		case LEFT:
			this.setX(this.getX() - 1 * speedMultiplier);
			break;
		case RIGHT:
			this.setX(this.getX() + 1 * speedMultiplier);
			break;
		}
		
	}

}
