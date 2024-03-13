import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends ImageView {
	Image image = new Image("sprites/Retro_Block.png");
	
	Player() {
		setImage(image);
		
	}
	
}
