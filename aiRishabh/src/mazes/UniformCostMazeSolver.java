package mazes;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UniformCostMazeSolver implements MazeSolver {
	private Maze maze;
	
	public UniformCostMazeSolver (Maze m){
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
		PriorityQueue <MazeCell> bag = new PriorityQueue <MazeCell> (10, new MazeCellComparator());
		boolean found = false;
		MazeCell current = null;
		bag.add(maze.getBegin());
		maze.getBegin().setCost(0);
		while (!found){
			if (bag.size() == 0) break;
			current = bag.poll();
			current.setVisited(true);
			
			// Check if target
			found = (current == maze.getTarget());
			
			//Get the neighbors
			MazeCell[] neighbors = maze.getNeighbors(current);
			for (MazeCell cell : neighbors){
				if (!cell.isVisited()){
				cell.setCost(current.getCost() + 1);
				bag.add(cell);
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
			int d0 = arg0.getCost(); 
			int d1 = arg1.getCost();
			return d0 - d1;
			
		}
		
	}
}
