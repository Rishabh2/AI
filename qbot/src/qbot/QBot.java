package qbot;

public abstract class QBot {
	private Vector position;
	private boolean reverse = false;
	
	public QBot() {
		this(new Vector(0, 0));
	}
	
	public QBot(Vector start) {
		position = start;
	}
	
	public void update(Snapshot snapshot) {
		Vector velocity = (reverse) ? getVelocity(snapshot).flip() : getVelocity(snapshot); 
		position.add(velocity);
		for (Block b : snapshot.getBlocks()) {
			b.applyNormalForce(this, snapshot);
		}
	}
	
	public abstract Vector getVelocity(Snapshot snapshot);
	
	public int getX() {
		return (int) position.getX();
	}
	
	public int getY() {
		return (int) position.getY();
	}

	public Vector getPosition() {
		return position;
	}
	
	public void setPosition(Vector position) {
		this.position = position;
	}
	
	public int getRadius() {
		return 10;
	}
	
	public void setReverse() {
		reverse = true;
	}
}
