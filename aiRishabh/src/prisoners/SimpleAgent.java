package prisoners;

public class SimpleAgent implements Agent {
	
	boolean desicion = false;

	@Override
	public boolean cooperate() {
		
		return desicion;
	}

	@Override
	public void remember(boolean otherPlayerDecision) {
		desicion = otherPlayerDecision;

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		desicion = false;
	}

}
