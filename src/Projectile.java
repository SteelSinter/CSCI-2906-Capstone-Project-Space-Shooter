import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Projectile extends GameObject {
	Direction direction;

	Projectile(Direction d, double x, double y) {
		game = Main.getGame();
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
			setY(getY() - 1 * speedMultiplier);
			break;
		case DOWN:
			setY(getY() + 1 * speedMultiplier);
			break;
		case LEFT:
			setX(getX() - 1 * speedMultiplier);
			break;
		case RIGHT:
			setX(getX() + 1 * speedMultiplier);
			break;
		}
		
		if (offScreen())
			game.removeObject(this);
		
	}

}
