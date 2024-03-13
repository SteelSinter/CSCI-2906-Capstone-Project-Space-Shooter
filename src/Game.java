import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class Game extends Application {
	public Player player;
	public Pane background;

	@Override
	public void start(Stage mainStage) {
		background = new Pane();
		Scene scene = new Scene(background, 700, 500);
		scene.setFill(new ImagePattern(new Image("sprites/Space.jpg")));
		
		initalize();
		
		mainStage.setScene(scene);
		mainStage.setTitle("Space Shooter");
		mainStage.show();
	}
	
	public void initalize() {
		player = new Player();
		background.getChildren().add(player);
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);

	}

}
