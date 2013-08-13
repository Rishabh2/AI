package qbot;

public class BasicBot extends QBot {

	@Override
	public Vector getVelocity(Snapshot snapshot) {
		return new Vector(Vector.RIGHT);
	}
}
