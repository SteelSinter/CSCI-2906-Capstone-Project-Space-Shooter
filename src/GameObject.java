
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

abstract class GameObject extends ImageView {
	enum Direction {UP, DOWN, LEFT, RIGHT}
	double speedMultiplier;
	static Game game;
	Rectangle hitbox;
	Image sprite;
	
	GameObject() {
		game = Main.getGame();
	}
	
	abstract void update();
	
	abstract void draw();
	
	boolean offScreen() {
		// TODO: Redefine offscreen check to check center of sprite
		return (getX() > Main.background.getWidth() || getY() > Main.background.getHeight() || getX() <= 0 || getY() <= 0);
	}
	
	Rectangle getHitbox() {
		return hitbox;
	}
}
