import java.io.IOException;

/**
 * Defines a type of enemy to be created in a specific pattern.
 */
public class EnemyWave {
	public enum Formation {SQUARE, SINE, TRIANGLE}
	private double startX = Main.scene.getWidth();
	private double startY = Main.scene.getHeight() / 10;
	private boolean mustKillAll; // If the player needs to kill all of them to progress
	private Formation formation;
	private Enemy.EnemyType type;
	private static Game game;
	private int count;

	/**
	 * Create an enemy wave.
	 * @param count
	 * @param type
	 * @param formation
	 */
	EnemyWave(int count, Enemy.EnemyType type, Formation formation) {
		game = Main.getGame();
		this.count = count;
		this.formation = formation;
		this.type = type;
	}
	
	EnemyWave(int count) {
		game = Main.getGame();
		this.count = count;
		formation = Formation.SQUARE;
		type = Enemy.EnemyType.RIGHTTOLEFT;
	}

	/**
	 * Start the wave.
	 * @throws IOException
	 */
	public void startWave() throws IOException {
		switch (formation) {
		case SQUARE:
			square(count);
			break;
		case SINE:
			sine(count);
			break;
		case TRIANGLE:
			triangle(count);
			break;
		}
	}

	/**
	 * Create a wave in a square shape.
	 * @param count
	 */
	private void square(int count) {
		Game game = Main.getGame();
		System.out.println("Creating square wave of " + count + " enemies");
		double x = startX, y = startY;

		for (int i = 0; i < count; i++) {
			x += ((int) (Math.random() * 100)) - 50;
			y += ((int) (Math.random() * 100)) - 50;
			Enemy.createEnemy(type, x, y);
			if (i % 6 == 0 && i != 0) {
				x += Main.SCREEN_WIDTH / 10;
				y = startY;
			} else {
				y += Main.SCREEN_HEIGHT / 8;
			}
			
		}
	}
	
	private void sine(int count) {
		System.out.println("Creating sine wave of " + count + " enemies");
		double x = startX, y = startY;
	}
	
	private void triangle(int count) {
		System.out.println("Creating triangle wave of " + count + " enemies");
		double x = startX, y = startY;
	}
	
}
