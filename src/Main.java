import java.io.File;
import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class Main extends Application {
	public Player player;
	public Pane background;
	public Scene scene;
	public HashMap<KeyCode, Boolean> keys = new HashMap<>();
	public boolean gameRunning = true;

	@Override
	public void start(Stage mainStage) {
		background = new Pane();
		scene = new Scene(background, 700, 500);
		scene.setFill(new ImagePattern(new Image("sprites/Space.jpg")));
		
		initalize();
		
		mainStage.setScene(scene);
		mainStage.setTitle("Space Shooter");
		mainStage.show();
		
		new Game().start(); // start game thread
	}
	
	public void initalize() {
		keys.put(KeyCode.W, false);
		keys.put(KeyCode.A, false);
		keys.put(KeyCode.S, false);
		keys.put(KeyCode.D, false);
		keys.put(KeyCode.SPACE, false);
		setupKeybinds();
		player = new Player();
		background.getChildren().add(player);
		System.out.println("Player added to scene.");
		
	}
	
	public void setupKeybinds() {
		scene.setOnKeyPressed(e -> {
			try {
				keys.put(e.getCode(), true);
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		});
		
		scene.setOnKeyReleased(e -> {
			try {
				keys.put(e.getCode(), false);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}
	
	public static void main(String[] args) {
		Application.launch(args);

	}

}

class Game extends Thread {
	@Override
	public void run() {
		while (Game running) {
			
		}
	}
	
	public void getInput() {
		
	}
}
