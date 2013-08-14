package prisoners;

public class RememberAgent implements Agent {
	int cooperated;
	int competed;
	@Override
	public boolean cooperate() {
		if (cooperated <= competed)
			return false;
		return true;
	}

	@Override
	public void remember(boolean otherPlayerDecision) {
		if (otherPlayerDecision)
			cooperated++;
		else
			competed++;

	}

	@Override
	public void reset() {
		cooperated = 0;
		competed = 0;
		
	}

}
