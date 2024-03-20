import javafx.scene.image.Image;

abstract class Enemy extends GameObject {
	protected enum EnemyType {RIGHTTOLEFT, SPINNER, TRACKER}
	Direction direction;
	Enemy() {
	}
	
	static void createEnemy(EnemyType type) {
		if (type == EnemyType.RIGHTTOLEFT) {
			game.addObject(new RightToLeft());
		} else if (type == EnemyType.SPINNER) {
			
		}
	}

}

class RightToLeft extends Enemy {
	
	RightToLeft() {
		setImage(new Image("sprites/spr_enemy.png"));
		System.out.println("RighttoLeftAdded");
		speedMultiplier = 2;
	}

	@Override
	void update() {
		if (offScreen() && getX() < -50)
			game.removeObject(this);
		
	}

	@Override
	void draw() {
		setX(getX() - 1 * speedMultiplier);
	}
	
}