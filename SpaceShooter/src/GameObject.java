/**
 * @author James Jesus
 */


import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Abstract class that defines all the functionality of every visual object in the game.
 */
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
		this.sprite = Main.resizeImage(sprite, (int) Main.SCREEN_WIDTH / 18, (int) Main.SCREEN_WIDTH / 18);
		speed = 1;
		setFill(Color.TRANSPARENT);
		setStroke(Color.RED);
	}

	/**
	 * Defines how to update the object.
	 */
	abstract void update();

	/**
	 * Defines what to do when the object is removed. USE isdead = true instead of Main.removeObject().
	 */
	abstract void destroy();

	/**
	 * Check if the object is off screen.
	 * @return
	 */
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

	/**
	 * Set the x offset of the sprite.
	 * @param d
	 */
	public void setXOffset(double d) {
		this.xOffset = d;
	}

	/**
	 * Set the y offset of the sprite.
	 * @param d
	 */
	public void setYOffset(double d) {
		this.yOffset = d;
	}
	
	public double getXOffset() {
		return xOffset;
	}
	
	public double getYOffset() {
		return yOffset;
	}

	/**
	 * Check if this enemy is dead.
	 * @return
	 */
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
