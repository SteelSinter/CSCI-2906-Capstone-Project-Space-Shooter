import javafx.scene.image.Image;

abstract class Enemy extends GameObject {
	protected enum EnemyType {RIGHTTOLEFT, SPINNER, TRACKER}
	Direction direction;
	
	Enemy() {}
	
	Enemy(Image sprite) {
		super(sprite);
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
		super(new Image("sprites/spr_enemy.png"));
		speed = 2;
	}

	@Override
	void update() {
		if (isOffScreen() && getX() < Main.scene.getHeight());
			game.removeObject(this);
		setX(getX() - 1 * speed);
		
	}
	
}