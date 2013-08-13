package qbot;

public class Block {
	private Vector position;
	private QBot[] bots;
	
	public Block(QBot[] bots, Vector position) {
		this.position = position;
		this.bots = bots;
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public int getX() {
		return (int) position.getX();
	}
	
	public int getY() {
		return (int) position.getY();
	}
	
	private boolean isTouching(int x, int y, int radius) {
		Vector distance = new Vector(x - position.getX(), y - position.getY());
		return distance.magnitude() < radius + getRadius();
	}
	
	public void applyNormalForce(QBot bot, Snapshot snap) {
		if (isTouching(bot.getX(), bot.getY(), bot.getRadius())) {
			Vector normal = new Vector(getX() - bot.getX(), getY() - bot.getY());
			position.add(normal.normalize());
			// Loop through to collide with other blocks if possible
			for (Block b : snap.getBlocks())
				if (b != this && isTouching(b.getX(), b.getY(), b.getRadius()))
					b.position.add(normal.normalize());
		}
	}
	
	public int getRadius() {
		return 10;
	}
}
