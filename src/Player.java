import java.util.ConcurrentModificationException;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Player extends GameObject {
	private double fireRate = 1;
	private double framesPassed = 0;
	private Game game;
	
	Player() {
		super();
		game = Main.getGame();
		speedMultiplier = 2;
		setImage(new Image("sprites/spr_player.png"));
	}
	
	public void update() {
		if (game.keys.get(KeyCode.SPACE)) {
			shoot();
			framesPassed++;
		} else {
			framesPassed = 0;
		}
		
		//if (framesPassed == 1 || framesPassed != 0 && framesPassed % 60 != 0) {
		//	shoot();
		//}
		
		printStatus();
	}
	
	public void draw() {
		if (game.keys.get(KeyCode.W)) {
			setY(getY() - 1 * speedMultiplier);
		}
		if (game.keys.get(KeyCode.A)) {
			setX(getX() - 1 * speedMultiplier);
		}
		if (game.keys.get(KeyCode.S)) {
			setY(getY() + 1 * speedMultiplier);
		}
		if (game.keys.get(KeyCode.D)) {
			setX(getX() + 1 * speedMultiplier);
		}
	}
	
	public void shoot() {
		game.addObject(new Projectile(Direction.RIGHT, getX() + getImage().getWidth(), getY() + getImage().getHeight() / 2));
	}
	
	public void printStatus() {
		System.out.println(getX());
		System.out.println(getY());
		System.out.println(offScreen());
	}
}
