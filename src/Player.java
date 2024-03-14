import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Player extends GameObject {
	Image image = new Image("sprites/spr_player.png");
	
	Player() {
		setImage(image);
	}
	
	public void update() {
		if (Main.keys.get(KeyCode.A)) {
			System.out.println("A");
		}
		
	}
	
	public void draw() {
		
	}
}
