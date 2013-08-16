package mazes;

public class MazeCellData {
	public MazeCell cell;
	public boolean visited;
	public boolean targetReached;
	public int targetNumber;
	public int cost;
	public int depth;
	public MazeCell parent;
	public int neighbors;
	public int unvisitedNeighbors;

	public MazeCellData(MazeCell cell) {
		this.cell = cell;
		visited = targetReached = false;
		cost = depth = neighbors = unvisitedNeighbors = targetNumber = 0;
		parent = null;
	}

	public MazeCellData(MazeCellData other) {
		this.cell = other.cell;
		this.visited = other.visited;
		this.cost = other.cost;
		this.depth = other.depth;
		this.parent = other.parent;
		this.neighbors = other.neighbors;
		this.unvisitedNeighbors = other.unvisitedNeighbors;
		this.targetReached = other.targetReached;
		this.targetNumber = other.targetNumber;
	}

	public void copyData(MazeCellData other) {
		this.cost = other.cost;
		this.targetNumber = other.targetNumber;
		this.neighbors = other.neighbors;
		this.unvisitedNeighbors = other.unvisitedNeighbors;
		this.depth = other.depth;

	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Cell: " + cell.toString() + "\n");
		str.append("Visited: " + visited + "\n");
		str.append("Cost: " + cost + "\n");
		str.append("Depth: " + depth + "\n");
		str.append("Parent: " + parent.toString() + "\n");
		str.append("Neighbors: " + neighbors + "\n");
		return str.toString();
	}
}