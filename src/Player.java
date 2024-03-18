import java.util.ConcurrentModificationException;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Player extends GameObject {
	
	Player() {
		super();
		speedMultiplier = 1.5;
		setImage(new Image("sprites/spr_player.png"));
	}
	
	public void update() {
		if (Main.keys.get(KeyCode.SPACE)) {
			shoot();
		}
		
		printStatus();
	}
	
	public void draw() {
		if (Main.keys.get(KeyCode.W)) {
			setY(getY() - 1 * speedMultiplier);
		}
		if (Main.keys.get(KeyCode.A)) {
			setX(getX() - 1 * speedMultiplier);
		}
		if (Main.keys.get(KeyCode.S)) {
			setY(getY() + 1 * speedMultiplier);
		}
		if (Main.keys.get(KeyCode.D)) {
			setX(getX() + 1 * speedMultiplier);
		}
	}
	
	public void shoot() {
		Platform.runLater(() -> {
			try {
				Main.addObject(new Projectile(Direction.RIGHT, getX(), getY()));
			} catch (ConcurrentModificationException ex) {
				// Don't worry about it
			} catch (Exception ex) {
				// Do nothing
			}
		});
		
	}
	
	public void printStatus() {
		System.out.println(getX());
		System.out.println(getY());
	}
}
