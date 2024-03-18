
import javafx.scene.image.ImageView;

abstract class GameObject extends ImageView {
	double speedMultiplier;
	
	enum Direction {UP, DOWN, LEFT, RIGHT}
	
	abstract void update();
	
	abstract void draw();
	
}
