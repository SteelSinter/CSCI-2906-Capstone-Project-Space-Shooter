import javafx.scene.image.ImageView;

abstract class GameObject extends ImageView {
	abstract void update();
	
	abstract void draw();
}
