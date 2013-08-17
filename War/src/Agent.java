
public interface Agent {
	
	public Move getMove();
	
	public void setID(int id);
	public int getID();
	
	public void setBoard(Board board);
	public int getScore();
	
	public String getName();
	public String getProperty();
}
