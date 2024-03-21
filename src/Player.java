import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Player extends GameObject {
	private double fireRate = 1.5;
	private double framesPassed = 0;
	
	Player() {
		super(new Image("sprites/spr_player.png"));
		speed = 2;
		setHitboxVisible(true);
	}
	
	public void update() {
		checkCollisions();
		
		if (game.keys.get(KeyCode.SPACE)) {
			framesPassed++;
		}
		
		if (!game.keys.get(KeyCode.SPACE))
			framesPassed = 0;
		
		if (framesPassed == 1 || (framesPassed % (60 / fireRate)) == 0 && framesPassed != 0) {
			System.out.println(framesPassed);
			shoot();
		}
		
		if (game.keys.get(KeyCode.W)) {
			setY(getY() - 1 * speed);
		}
		if (game.keys.get(KeyCode.A)) {
			setX(getX() - 1 * speed);
		}
		if (game.keys.get(KeyCode.S)) {
			setY(getY() + 1 * speed);
		}
		if (game.keys.get(KeyCode.D)) {
			setX(getX() + 1 * speed);
		}
		
		//printStatus();
	}
	
	@Override
	public void destroy() {
		isDead = true;
	}
	
	public void shoot() {
		game.addObject(new Projectile(Direction.RIGHT, getX() + getWidth(), getY() + (getHeight() / 2)));
	}
	
	public void checkCollisions() {
		try {
			game.lock.readLock().lock();
			for (GameObject o: game.enemyObjects) {
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
	
	public void printStatus() {
		System.out.println(getX());
		System.out.println(getY());
		System.out.println("Off screen: " + isOffScreen());
	}
}
