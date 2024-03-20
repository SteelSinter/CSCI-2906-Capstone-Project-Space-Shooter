import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class Main extends Application {
	public Player player;
	public static Pane background;
	public static Scene scene;
	public static boolean gamePaused = false;
	public static Game game;
	

	@Override
	public void start(Stage mainStage) {
		background = new Pane();
		scene = new Scene(background, 700, 500);
		scene.setFill(new ImagePattern(new Image("sprites/Space.jpg")));
		
		mainStage.setScene(scene);
		mainStage.setTitle("Space Shooter");
		mainStage.show();
		
		game = new Game();
		
		game.start(); // start game thread
	}
	
	public static void addObject(GameObject o) {
		background.getChildren().add(o);
	}
	public static void addObject(GameObject o, double x, double y) {
		background.getChildren().add(o);
		o.setX(x);
		o.setY(y);
	}
	
	public static void removeObject(GameObject o) {
		background.getChildren().remove(o);
	}
	
	public static Game getGame() {
		return game;
	}
	
	public static void main(String[] args) {
		Application.launch(args);

	}

}

class Game extends Thread {
	private final int FPS = 60;
	final ReadWriteLock lock = new ReentrantReadWriteLock();
	public HashMap<KeyCode, Boolean> keys = new HashMap<>();
	public ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	@Override
	public void run() {
		keys.put(KeyCode.W, false);
		keys.put(KeyCode.A, false);
		keys.put(KeyCode.S, false);
		keys.put(KeyCode.D, false);
		keys.put(KeyCode.SPACE, false);
		keys.put(KeyCode.DIGIT1, false);
		
		// Setup key events
		Main.scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.DIGIT1)
				new EnemyWave(4).startWave();
			try {
				keys.put(e.getCode(), true);
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		});
		
		Main.scene.setOnKeyReleased(e -> {
			try {
				keys.put(e.getCode(), false);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		addObject(new Player());
		System.out.println("Player added to scene.");
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
		try {
			lock.readLock().lock();
			for (GameObject o: gameObjects) {
				o.update();
				o.draw();
			}
		} catch (NullPointerException ex) {
			System.out.println("Null pointer exception ");
		} finally {
			lock.readLock().unlock();
		}
		
	}
	
	public void addObject(GameObject o) {
		new Thread(() -> {
			lock.writeLock().lock();
			try {
				gameObjects.add(o);
				Platform.runLater(() -> {
					Main.addObject(o);
				});
			} finally {
				lock.writeLock().unlock();
			}
		}).start();
		
		
	}
	
	public void addObject(GameObject o, double x, double y) {
		new Thread(() -> {
			lock.writeLock().lock();
			try {
				gameObjects.add(o);
				Platform.runLater(() -> {
					Main.addObject(o, x, y);
				});
			} finally {
				lock.writeLock().unlock();
			}
		}).start();
		
		
	}
	
	public void removeObject(GameObject o) {
		new Thread(() -> {
			lock.writeLock().lock();
			try {
				gameObjects.remove(o);
				Platform.runLater(() -> {
					Main.removeObject(o);
				});
			} finally {
				lock.writeLock().unlock();
			}
		}).start();
	}
}         