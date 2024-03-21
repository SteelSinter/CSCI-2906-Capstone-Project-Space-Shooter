/**
 * @author James Jesus
 */
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

abstract class GameObject extends Rectangle {
	enum Direction {UP, DOWN, LEFT, RIGHT}
	protected static Game game;
	protected boolean isDead, isHitboxVisible, isEnemy;
	Image sprite;
	double speed;
	
	GameObject() {
		game = Main.getGame();
		speed = 1;
		setFill(Color.TRANSPARENT);
		setWidth(50);
		setHeight(50);
	}
	
	GameObject(Image sprite) {
		game = Main.getGame();
		this.sprite = sprite;
		speed = 1;
		setFill(Color.TRANSPARENT);
		setWidth(sprite.getWidth());
		setHeight(sprite.getHeight());
	}
	/**
	 * Check if this object is colliding with another object.
	 * @param other other object
	 * @return true if they are colliding
	 */
	public boolean colliding(GameObject other) {
		if (this.getBoundsInParent().intersects(other.getBoundsInParent())) {
			return true;
		}
		
		return false;
	}
	
	abstract void update();
	
	abstract void destroy();
	
	public boolean isOffScreen() {
		return (getX() < 0 - sprite.getWidth() ||
				getX() > Main.scene.getWidth() ||
				getY() < 0 - sprite.getHeight() ||
				getY() > Main.scene.getHeight());
	}
	
	public void toggleHitbox() {
		isHitboxVisible = !isHitboxVisible();
		if (isHitboxVisible) {
			setStroke(Color.RED);
		} else {
			setStroke(Color.TRANSPARENT);
		}
	}
	
	public void setHitboxVisible(boolean b) {
		if (b) {
			setStroke(Color.RED);
		} else {
			setStroke(Color.TRANSPARENT);
		}
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public boolean isEnemy() {
		return isEnemy;
	}
	
	public boolean isHitboxVisible() {
		return isHitboxVisible;
	}
	
	public Image getSprite() {
		return sprite;
	}
}
