
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final double SCREEN_WIDTH = screenSize.getWidth();
	public static final double SCREEN_HEIGHT = screenSize.getHeight();
	public Player player;
	public static Pane background;
	public static Pane pane;
	public static Scene scene;
	public static Game game;
	public static Text pointCounter;
	public static Text lifeCounter;

	@Override
	public void start(Stage mainStage) {
		try {
			pointCounter = new Text("0");
			lifeCounter = new Text("3");
			background = new Pane();
			pane = new Pane();
			pane.getChildren().addAll(background, pointCounter, lifeCounter);
			scene = new Scene(pane, (SCREEN_WIDTH * 0.9), SCREEN_HEIGHT * 0.9);
			scene.setFill(new ImagePattern(new Image("sprites/spr_background.png"), 0, 0, 64, 64, false));
			lifeCounter.setStroke(Color.WHITE);
			lifeCounter.setX(scene.getWidth() - (scene.getWidth() / 3));
			lifeCounter.setFont(new Font("", 23));
			
			mainStage.setScene(scene);
			mainStage.setTitle("Space Shooter");
			mainStage.show();
			
			game = new Game();
			
			game.start(); // start game thread
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addObject(ImageView i) {
		background.getChildren().add(i);
	}
	
	public static void addObject(Label l) {
		background.getChildren().add(l);
	}
	
	public static void addObject(Rectangle r) {
		background.getChildren().add(r);
	}
	
	public static void addObject(ImageView i, double x, double y) {
		background.getChildren().add(i);
		i.setX(x);
		i.setY(y);
	}
	
	public static void addObject(Rectangle r, double x, double y) {
		background.getChildren().add(r);
		r.setX(x);
		r.setY(y);
	}
	
	public static void removeObject(Node n) {
		background.getChildren().remove(n);
	}
	
	public static Game getGame() {
		return game;
	}
	
	public static Image resizeImage(Image image, int targetWidth, int targetHeight) {
		try {
			BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D graphics2D = resizedImage.createGraphics();
		    graphics2D.drawImage(SwingFXUtils.fromFXImage(image, null) , 0, 0, targetWidth, targetHeight, null);
		    graphics2D.dispose();
		    return SwingFXUtils.toFXImage(resizedImage, null);
		} catch (Exception ex) {
			System.out.println("Exception when resizing image: ");
			ex.printStackTrace();
			return null;
		}
	    
	}
	
	public static void main(String[] args) {
		Application.launch(args);

	}

}

class Game extends Thread {
	private final int FPS = 60;
	private long startTime, endTime, duration;
	public static boolean gameRunning = true;
	public static boolean gamePaused = false;
	final ReadWriteLock lock = new ReentrantReadWriteLock();
	public HashMap<KeyCode, Boolean> keys = new HashMap<>();
	public ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	public ArrayList<GameObject> enemyObjects = new ArrayList<GameObject>();
	public ArrayList<GameObject> playerProjectiles = new ArrayList<GameObject>();
	public EnemyWave[] level1 = {
			new EnemyWave(4),
			new EnemyWave(6),
			new EnemyWave(6),
			new EnemyWave(25),
	};
	
	public GameOrder gameOrder = new GameOrder();
	
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
			if (e.getCode() == KeyCode.P) {
				gamePaused = !gamePaused;
				System.out.println("Game paused");
			}
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
		
		try {
			addObject(new Player(), Main.SCREEN_WIDTH / 9, Main.SCREEN_HEIGHT / 2 - (Main.SCREEN_HEIGHT / 10) / 2);
		} catch (Exception e1) {
			System.out.println("Exception when adding player: ");
			e1.printStackTrace();
		}
		System.out.println("Player added to scene.");
		System.out.println("Game loop started");
		while (gameRunning) {
			if (!gamePaused) {
				try {
					startTime = System.currentTimeMillis();
					gameOrder.next();
					updateGame();
					Platform.runLater(() -> {
						drawSprites();
					});
					endTime = System.currentTimeMillis();
					duration = endTime - startTime;
					Thread.sleep((1000 - duration) / FPS);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("Exception in game loop");
					ex.printStackTrace();
				}
			} else {
				System.out.print(""); // Removing this breaks the pause button
			}
		}
	}
	
	public void updateGame() {
		try {
			lock.readLock().lock();
			for (GameObject o: gameObjects) {
				// Remove dead objects
				if (o.isDead()) {
					removeObject(o);
					continue;
				}
				o.update();
			}
		} catch (NullPointerException ex) {
			System.out.println("Null pointer exception");
		} finally {
			lock.readLock().unlock();
		}
		
	}
	
	public void drawSprites() {
		try {
			lock.readLock().lock();
			// Clear sprites
			Main.background.getChildren().clear();
			
			for (GameObject o: gameObjects) {
				Main.addObject(new ImageView(o.getSprite()), o.getX() + o.getXOffset(), o.getY() + o.getYOffset());
				// If the hitbox is visible, add it to the scene
				if (o.hitboxVisible()) {
					Main.addObject((Rectangle) o, o.getX(), o.getY());
				}
			}
		} catch (NullPointerException ex) {
			System.out.println("Null pointer exception");
		} catch (Exception ex) {
			System.out.println("Exception in drawSprites()");
			ex.printStackTrace();
		} finally {
			lock.readLock().unlock();
		}
	}
	
	public void addObject(GameObject o) {
		new Thread(() -> {
			lock.writeLock().lock();
			try {
				gameObjects.add(o);
				if (o.isEnemy() || o instanceof Powerup) {
					enemyObjects.add(o);
				}
				if (o instanceof Projectile && !o.isEnemy()) {
					playerProjectiles.add(o);
				}
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
				if (o.isEnemy() || o instanceof Powerup) {
					enemyObjects.add(o);
				}
				if (o instanceof Projectile && !o.isEnemy()) {
					playerProjectiles.add(o);
				}
				o.setX(x);
				o.setY(y);
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
				if (o.isEnemy()) {
					enemyObjects.remove(o);
				}
				if (o instanceof Projectile && !o.isEnemy()) {
					playerProjectiles.remove(o);
				}
			} finally {
				lock.writeLock().unlock();
			}
		}).start();
	}
}

class GameOrder {
	private LinkedList<Object> gameOrder = new LinkedList<Object>();
	private int frameInterval;
	Game game = Main.getGame();
	
	GameOrder() {
		gameOrder.add(new EnemyWave(6));
		gameOrder.add(new EnemyWave(8));
		gameOrder.add(new EnemyWave(9));
		gameOrder.add(new EnemyWave(12));
		//gameOrder.add(new Powerup());
		frameInterval = 0;
	}
	
	protected void next() throws IOException {
		frameInterval++;
		if (!(frameInterval % 300 == 0)) {
			return;
		}
		if (gameOrder.isEmpty()) {
			gameOrder.add(new EnemyWave((int) (Math.random() * 10)));
			return;
		}
		Object next = gameOrder.pop();
		if (next instanceof Powerup) {
			game.addObject((Powerup) next);
			return;
		}
		if (next instanceof EnemyWave) {
			((EnemyWave) next).startWave();
			return;
		}
	}
}