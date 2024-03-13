import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class Game extends Application {
	public Player player;
	public Pane background;
	public Scene scene;

	@Override
	public void start(Stage mainStage) {
		background = new Pane();
		scene = new Scene(background, 700, 500);
		scene.setFill(new ImagePattern(new Image("sprites/Space.jpg")));
		
		initalize();
		
		mainStage.setScene(scene);
		mainStage.setTitle("Space Shooter");
		mainStage.show();
	}
	
	public void initalize() {
		player = new Player();
		background.getChildren().add(player);
		System.out.println("Player added to scene.");
		setupEvents();
		
	}
	
	public void setupEvents() {
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case W:
				System.out.println("Up");
				break;
			case A:
				System.out.println("Left");
				break;
			case S:
				System.out.println("Down");
				break;
			case D:
				System.out.println("Right");
				break;
			}
		});
	}
	
	public static void main(String[] args) {
		Application.launch(args);

	}

}
