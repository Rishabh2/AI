package prisoners;

public class VariableAgent implements Agent{

	private double niceness;
	
	public VariableAgent(double niceness){
		this.niceness = niceness;
	}
	
	@Override
	public boolean cooperate() {
		if (Math.random() < niceness)
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
