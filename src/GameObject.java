/**
 * @author James Jesus
 */


import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

abstract class GameObject extends Rectangle {
	enum Direction {UP, DOWN, LEFT, RIGHT}
	protected static Game game;
	protected boolean isDead, hitboxVisible, isEnemy;
	Image sprite;
	double speed, xOffset = 0, yOffset = 0;
	
	GameObject() {
		game = Main.getGame();
		speed = 1;
		setFill(Color.TRANSPARENT);
		setStroke(Color.RED);
		setWidth(50);
		setHeight(50);
	}
	
	GameObject(Image sprite) {
		game = Main.getGame();
		this.sprite = Main.resizeImage(sprite, (int) Main.SCREEN_WIDTH / 10, (int) Main.SCREEN_WIDTH / 10);
		speed = 1;
		setFill(Color.TRANSPARENT);
		setStroke(Color.RED);
	}
	
	abstract void update();
	
	abstract void destroy();
	
	public boolean isOffScreen() {
		return (getX() < 0 - sprite.getWidth() ||
				getX() > Main.scene.getWidth() ||
				getY() < 0 - sprite.getHeight() ||
				getY() > Main.scene.getHeight());
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
	
	/**
	 * Toggle the visibility of the hitbox.
	 */
	public void toggleHitbox() {
		hitboxVisible = !hitboxVisible;
	}
	
	public void setHitboxVisible(boolean b) {
		if (b) {
			hitboxVisible = true;
		} else {
			hitboxVisible = false;
		}
	}
	
	public void setXOffset(double d) {
		this.xOffset = d;
	}
	
	public void setYOffset(double d) {
		this.yOffset = d;
	}
	
	public double getXOffset() {
		return xOffset;
	}
	
	public double getYOffset() {
		return yOffset;
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public boolean isEnemy() {
		return isEnemy;
	}
	
	public boolean hitboxVisible() {
		return hitboxVisible;
	}
	
	public Image getSprite() {
		return sprite;
	}
}
