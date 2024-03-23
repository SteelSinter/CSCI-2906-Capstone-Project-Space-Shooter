package application;
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
	
	public void checkCollisions() {
		try {
			game.lock.readLock().lock();
			for (GameObject o: game.playerProjectiles) {
				if (colliding(o)) {
					this.destroy();
					o.destroy();
				}
			}
		} catch (NullPointerException ex) {
			System.out.println("Null pointer exception ");
		} finally {
			game.lock.readLock().unlock();
		}
	}

}

class RightToLeft extends Enemy {
	
	RightToLeft() {
		super(new Image("sprites/spr_enemy.png"));
		isEnemy = true;
		setHitboxVisible(true);
		setHeight(sprite.getHeight() / 3);
		setYOffset(-(sprite.getHeight() / 3));
		speed = 2;
	}

	@Override
	void update() {
		checkCollisions();
		if (getX() < -50)
			game.removeObject(this);
		setX(getX() - 1 * speed);
		
	}
	
	@Override
	public void destroy() {
		isDead = true;
	}
	
}