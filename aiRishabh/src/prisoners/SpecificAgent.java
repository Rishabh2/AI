package prisoners;

public class SpecificAgent implements Agent {
		int counter;
		boolean lastcooperate;
	@Override
	public boolean cooperate() {
		if (counter < 2){
			counter ++;
			lastcooperate = true;
			return true;
		}
			else if (!lastcooperate)
				return true;
		return false;
	}

	@Override
	public void remember(boolean otherPlayerDecision) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		counter = 0;

	}

}
