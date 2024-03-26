import javafx.scene.image.Image;

import java.util.LinkedList;

/**
 * Visual effect that does not interact with anything.
 */
public abstract class Effect extends GameObject {
	LinkedList<Image> sprites = new LinkedList<Image>();
	int currentSprite;
	int currentFrame;

}

/**
 * Explosion effect.
 */
class Explosion extends Effect {
	Explosion(int width, int height) {
		sprites.add(Main.resizeImage(new Image("sprites/spr_explosion1.png"), width, height));

		sprites.add(Main.resizeImage(new Image("sprites/spr_explosion2.png"), width, height));

		sprites.add(Main.resizeImage(new Image("sprites/spr_explosion3.png"), width, height));

		sprites.add(Main.resizeImage(new Image("sprites/spr_explosion4.png"), width, height));

		sprites.add(Main.resizeImage(new Image("sprites/spr_explosion5.png"), width, height));

		sprites.add(Main.resizeImage(new Image("sprites/spr_explosion6.png"), width, height));

		sprites.add(Main.resizeImage(new Image("sprites/spr_explosion7.png"), width, height));

		sprites.add(Main.resizeImage(new Image("sprites/spr_explosion8.png"), width, height));


	}

	@Override
	void update() {
		try {
			sprite = sprites.get(currentSprite);
			currentFrame++;
			if (currentFrame % 2 == 0) {
				currentSprite++;
			}
			if (currentFrame >= 119) {
				destroy();
			}
		} catch (Exception ex) {
			destroy();
		}

	}

	@Override
	void destroy() {
		isDead = true;
	}
}
