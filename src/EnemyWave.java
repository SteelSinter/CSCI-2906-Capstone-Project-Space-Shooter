import java.io.IOException;

public class EnemyWave {
	private enum EnemyType {RIGHTTOLEFT, STAY, SPIN}
	public enum Formation {SQUARE, SINE, TRIANGLE}
	private double startX = Main.scene.getWidth();
	private double startY = Main.scene.getHeight() / 4;
	private boolean mustKillAll; // If the player needs to kill all of them to progress
	private Formation formation;
	private EnemyType type;
	private static Game game;
	private int count;
	
	
	EnemyWave(int count, EnemyType type, Formation formation) {
		game = Main.getGame();
		this.count = count;
		this.formation = formation;
		this.type = type;
	}
	
	EnemyWave(int count) {
		game = Main.getGame();
		this.count = count;
		formation = Formation.SQUARE;
		type = EnemyType.RIGHTTOLEFT;
	}
	
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
	
	private void square(int count) throws IOException {
		Game game = Main.getGame();
		System.out.println("Creating square wave of " + count + " enemies");
		double x = startX, y = startY;
		for (int i = 0; i < count; i++) {
			switch (type) {
			case RIGHTTOLEFT:
				game.addObject(new RightToLeft(), x, y);
				break;
			case STAY:
				game.addObject(new Stay(), x, y);
			case SPIN:
				//
				break;
			default:
				game.addObject(new RightToLeft(), x, y);
				break;
			}
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
