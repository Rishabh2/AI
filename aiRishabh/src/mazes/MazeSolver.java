package mazes;

public interface MazeSolver {
	public String getName();
	public void setMaze(Maze m);
	public Maze getMaze();
	public Maze getSolution();
	public MazeSolverData getData();
}