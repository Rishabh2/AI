package prisoners;

public class RandomAgent implements Agent {

	
	
	@Override
	public boolean cooperate() {
		if (Math.random() < 0.5)
			return true;
		return false;
	}

	@Override
	public void remember(boolean otherPlayerDecision) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
