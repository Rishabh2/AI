package mazes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarMazeSolver implements MazeSolver {
	private Maze maze;
	public AStarMazeSolver (Maze m){
		maze = m;
	}

	@Override
	public String getName() {
		return "Greedy BFS Maze Solver";
	}

	@Override
	public void setMaze(Maze m) {
		maze = m;

	}

	@Override
	public Maze getMaze() {
		return maze;
	}

	@Override
	public Maze getSolution() {
		PriorityQueue <MazeCell> openbag = new PriorityQueue <MazeCell> (10, new MazeCellComparator());
		ArrayList <MazeCell> closedbag = new ArrayList <MazeCell> ();
		boolean found = false;
		MazeCell current = null;
		openbag.add(maze.getBegin());
		maze.getBegin().setCost(0);
		while (!found){
			if (openbag.size() == 0) break;
			current = openbag.poll();
			closedbag.add(current);
			current.setVisited(true);
			
			// Check if target
			found = (current == maze.getTarget());
			
			//Get the neighbors
			MazeCell[] neighbors = maze.getNeighbors(current);
			for (MazeCell cell : neighbors){
				if (!cell.isVisited()){
				cell.setCost(current.getCost() + 1);
				cell.setScore(maze);
				openbag.add(cell);
				cell.setParent(current);
				cell.setVisited(true);
				}
			}
		}
		// mark all cells
		maze.unmark();
		while(current.getParent() != null){
			current.setVisited(true);
			current = current.getParent();
		}
		
		return maze;
	}

	@Override
	public MazeSolverData getData() {
		// TODO Auto-generated method stub
		return null;
	}
	class MazeCellComparator implements Comparator <MazeCell> {

		@Override
		public int compare(MazeCell arg0, MazeCell arg1) {
			return arg0.getF() - arg1.getF();
			
		}
		
	}
}
