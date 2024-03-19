
abstract class Enemy extends GameObject {
	Direction direction;
	protected enum EnemyType {LEFTTORIGHT, SPINNER, TRACKER}
	Enemy() {
		super();
	}

}

class RightToLeft extends Enemy {
	
	RightToLeft() {
		speedMultiplier = 1.2;
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		
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
		
	}
	
}