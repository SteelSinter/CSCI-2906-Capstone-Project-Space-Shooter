
public class EnemyWave {
	private Game game = Main.getGame();
	private boolean mustKillAll; // If the player needs to kill all of them to progress
	public enum Formation {SQUARE, SINE, TRIANGLE}
	private Formation formation;
	private int count;
	private double startX = Main.scene.getWidth();
	private double startY = Main.scene.getHeight() / 4;
	
	EnemyWave(int count, Formation formation, boolean mustKillAll, Enemy enemyType) {
		this.count = count;
		this.formation = formation;
		this.mustKillAll = mustKillAll;
	}
	
	EnemyWave(int count) {
		this.count = count;
	}
	
	public void startWave() {
		System.out.println("Creating wave of " + count + " enemies");
		double x = startX, y = startY;
		for (int i = 0; i < count; i++) {
			game.addObject(new RightToLeft(), x, y);
			if (i % 5 == 0 && i != 0) {
				x += 50;
				y = startY;
			}
			y += 50;
			
		}
	}
	
}
