import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Player extends GameObject {
	private double speedMultiplier = 1.5;
	Image image = new Image("sprites/spr_player.png");
	//private static ArrayList controls
	
	Player() {
		setImage(image);
	}
	
	public void update() {
		
		printStatus();
	}
	
	public void draw() {
		if (Main.keys.get(KeyCode.W)) {
			this.setY(this.getY() - 1 * speedMultiplier);
		}
		if (Main.keys.get(KeyCode.A)) {
			this.setX(this.getX() - 1 * speedMultiplier);
		}
		if (Main.keys.get(KeyCode.S)) {
			this.setY(this.getY() + 1 * speedMultiplier);
		}
		if (Main.keys.get(KeyCode.D)) {
			this.setX(this.getX() + 1 * speedMultiplier);
		}
		if (Main.keys.get(KeyCode.SPACE)) {
			shoot();
		}
	}
	
	public void shoot() {
		System.out.println(this.getParent());
	}
	
	public void printStatus() {
		System.out.println(getX());
		System.out.println(getY());
	}
}
