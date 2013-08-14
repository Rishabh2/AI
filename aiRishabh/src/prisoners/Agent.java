package prisoners;

public interface Agent {
	public boolean cooperate();
	
	public void remember(boolean otherPlayerDecision);
	
	public void reset();
}
