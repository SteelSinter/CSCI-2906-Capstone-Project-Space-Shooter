package application;

public class EnemyWave {
	private Game game;
	private Class<Enemy> type;
	private boolean mustKillAll; // If the player needs to kill all of them to progress
	public enum Formation {SQUARE, SINE, TRIANGLE}
	private Formation formation;
	private int count;
	private double startX = Main.scene.getWidth();
	private double startY = Main.scene.getHeight() / 4;
	
	EnemyWave(int count, Class<Enemy> type, Formation formation) {
		game = Main.getGame();
		this.count = count;
		this.formation = formation;
		this.type = type;
	}
	
	EnemyWave(int count) {
		game = Main.getGame();
		this.count = count;
		formation = Formation.SQUARE;
	}
	
	public void startWave() {
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
	
	private void square(int count) {
		System.out.println("Creating square wave of " + count + " enemies");
		double x = startX, y = startY;
		for (int i = 0; i < count; i++) {
			Main.getGame().addObject(new  (GameObject) type, x, y);
			if (i % 5 == 0 && i != 0) {
				x += 50;
				y = startY;
			} else {
				y += 50;
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
