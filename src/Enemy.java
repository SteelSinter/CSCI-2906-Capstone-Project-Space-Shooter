
import java.io.IOException;

import javafx.scene.image.Image;

/**
 * Enemy
 */
abstract class Enemy extends GameObject {
	protected enum EnemyType {RIGHTTOLEFT, SPINNER, TRACKER, STAY}
	Direction direction;
	
	Enemy() {}

	/**
	 * Create an enemy with a sprite.
	 * @param sprite
	 */
	Enemy(Image sprite) {
		super(sprite);
	}
	
	static void createEnemy(EnemyType type) {
		createEnemy(type, 0, 0);
	}

	/**
	 * Create an enemy at the given location.
	 * @param type Enemy type
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	static void createEnemy(EnemyType type, double x, double y) {
		if (type == EnemyType.RIGHTTOLEFT) {
			game.addObject(new RightToLeft(), x, y);
		} else if (type == EnemyType.STAY) {
			game.addObject(new Stay(), x, y);
		}
	}

	/**
	 * Check if this is colliding with and player projectiles.
	 */
	public void checkCollisions() {
		try {
			game.lock.readLock().lock();
			for (GameObject o: game.playerProjectiles) {
				if (colliding(o)) {
					this.destroy();
					o.destroy();
					if (this instanceof RightToLeft) {
						game.addPoints(1000);
						Main.pointCounter.setText(String.valueOf(game.getPoints()));
					} else if (this instanceof Stay) {
						game.addPoints(500);
						Main.pointCounter.setText(String.valueOf(game.getPoints()));
					}
				}
			}
		} catch (NullPointerException ex) {
			System.out.println("Null pointer exception ");
		} finally {
			game.lock.readLock().unlock();
		}
	}

}

/**
 * A simple enemy that moves across the screen from right to left.
 */
class RightToLeft extends Enemy {
	
	RightToLeft() {
		super(new Image("sprites/spr_enemy.png"));
		isEnemy = true;
		//setHitboxVisible(true);
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
		game.addObject(new Explosion((int) getWidth(), (int) getWidth()), getX(), getY());
	}
	
}

/**
 * An enemy that stops moving and shoots a few times before continuing off screen.
 */
class Stay extends Enemy {
	private int updates, fireDelay = 0, randFireOffset = ((int) (Math.random() * 100)), shotsTaken;
	
	Stay() {
		super(new Image("sprites/spr_stay.png"));
		isEnemy = true;
		setWidth(sprite.getWidth() * .8);
		setHeight(sprite.getHeight() * .8);
		this.sprite = Main.resizeImage(sprite, (int) Main.SCREEN_WIDTH / 22, (int) Main.SCREEN_WIDTH / 22);
		speed = 6;
		fireDelay += randFireOffset;
		//this.setHitboxVisible(true);
	}

	@Override
	void update() {
		checkCollisions();
		if (shotsTaken >= 5) {
			updates = 0;
		}
		if (updates >= 100) {
			if (fireDelay >= 100) {
				shoot();
				shotsTaken++;
				fireDelay = 0;
			} else {
				fireDelay++;
			}

		} else {
			setX(getX() - 1 * speed);
			updates++;
		}


	}

	@Override
	void destroy() {
		this.isDead = true;
		game.addObject(new Explosion((int) getWidth(), (int) getWidth()), getX(), getY());
	}

	public void shoot() {
		game.addObject(new Projectile(Direction.LEFT, getX() + (getWidth() / 2), getY() + (getHeight() / 2), true));
	}
}