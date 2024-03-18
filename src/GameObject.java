
import javafx.scene.image.ImageView;

abstract class GameObject extends ImageView {
	double speedMultiplier;
	
	enum Direction {UP, DOWN, LEFT, RIGHT}
	
	abstract void update();
	
	abstract void draw();
	
	boolean offScreen() {
		// TODO: Redefine offscreen check to check center of sprite
		return (getX() > Main.background.getWidth() || getY() > Main.background.getHeight() || getX() <= 0 || getY() <= 0);
	}
}
