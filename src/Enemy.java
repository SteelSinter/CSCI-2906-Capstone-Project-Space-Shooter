
import java.io.IOException;

import javafx.scene.image.Image;

abstract class Enemy extends GameObject {
	protected enum EnemyType {RIGHTTOLEFT, SPINNER, TRACKER, STAY}
	Direction direction;
	
	Enemy() {}
	
	Enemy(Image sprite) {
		super(sprite);
	}
	
	static void createEnemy(EnemyType type) {
		if (type == EnemyType.RIGHTTOLEFT) {
			try {
				game.addObject(new RightToLeft());
			} catch (Exception e) {
				System.out.println("Exception in createEnemy()");
				e.printStackTrace();
			}
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
		setWidth(sprite.getWidth());
		setHeight(sprite.getHeight() / 4.3);
		setYOffset(-(sprite.getHeight() / 3));
		speed = 4;
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

class Stay extends Enemy {
	private boolean stopped = false;
	
	Stay() {
		super(new Image("sprites/spr_stay"));
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void destroy() {
		// TODO Auto-generated method stub
		
	}
}