import java.io.File;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class Main extends Application {
	public Player player;
	public static Pane background;
	public Scene scene;
	public static HashMap<KeyCode, Boolean> keys = new HashMap<>();
	public static boolean gamePaused = false;
	public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

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
		// Initialize hashmap
		keys.put(KeyCode.W, false);
		keys.put(KeyCode.A, false);
		keys.put(KeyCode.S, false);
		keys.put(KeyCode.D, false);
		keys.put(KeyCode.SPACE, false);
		
		// Setup key events
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
		
		addObject(new Player());
		System.out.println("Player added to scene.");
	}
	
	public static void addObject(GameObject o) {
		background.getChildren().add(o);
		gameObjects.add(o);
	}
	
	public static void removeObject(GameObject o) {
		background.getChildren().remove(o);
		gameObjects.remove(o);
	}
	
	public static void main(String[] args) {
		Application.launch(args);

	}

}

class Game extends Thread {
	private final int FPS = 60;
	Lock lock = new ReentrantLock();
	@Override
	public void run() {
		System.out.println("Game loop started");
		while (!Main.gamePaused) {
			updateGame();
			try {
				Thread.sleep(1000 / FPS);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void updateGame() {
		for (GameObject o: Main.gameObjects) {
			o.update();
			o.draw();
		}
	}
}         