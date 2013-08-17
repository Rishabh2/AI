package mazes;

public class TestSolver {
	public static void main(String[] args) {
		String[] mazes = { "mediummaze", "smallmaze", "openmaze", "bigmaze.txt", "astar"};
		long time = 0;
		for (String maze : mazes) {
			time = System.currentTimeMillis();
			MazeSolver solver = new AStarMazeSolver(new Maze(maze));
			new MazeView(solver.getSolution());
			System.out.println("Maze " + maze);
			System.out.println(System.currentTimeMillis() - time);
			//System.out.println(solver.getData().toString());
		}
	}
}