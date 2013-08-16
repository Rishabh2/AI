package mazes;

import java.util.ArrayList;

public class BFSMazeSolver implements MazeSolver {
	private Maze maze;
	
	public BFSMazeSolver (Maze m){
		maze = m;
	}

	@Override
	public String getName() {
		return "BFS Maze Solver";
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
		maze.getBegin().setCost(0);
		while (!found){
			if (bag.size() == 0) break;
			current = bag.remove(0);
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
				cell.setCost(current.getCost() + 1);
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
