package mazes;

public class TestSolver {
	public static void main(String[] args) {
		String[] mazes = { "mediummaze", "smallmaze", "openmaze"};
		for (String maze : mazes) {
			MazeSolver solver = new BFSMazeSolver(new Maze(maze));
			new MazeView(solver.getSolution());
			System.out.println("Maze " + maze);
			//System.out.println(solver.getData().toString());
		}
	}
}