package mazes;

import java.util.ArrayList;

public class DFSMazeSolver implements MazeSolver {
	private Maze maze;
	
	public DFSMazeSolver (Maze m){
		maze = m;
	}

	@Override
	public String getName() {
		return "DFS Maze Solver";
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
		ArrayList <MazeCell> bag = new ArrayList <MazeCell> ();
		boolean found = false;
		MazeCell current = null;
		bag.add(maze.getBegin());
		while (!found){
			if (bag.size() == 0) break;
			current = bag.remove(bag.size() - 1);
			current.setVisited(true);
			
			// Check if target
			found = (current == maze.getTarget());
			
			//Get the neighbors
			MazeCell[] neighbors = maze.getNeighbors(current);
			for (MazeCell cell : neighbors){
				if (!cell.isVisited()){
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

}
