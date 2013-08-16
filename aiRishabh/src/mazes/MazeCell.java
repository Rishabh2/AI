package mazes;
/**
 * MazeCell is the elementary building block of a maze, representing a single cell in 2D space.
 * @author Keshav
 */

public class MazeCell {
	private int x, y;
	private int state;

	// a reference to data used for pathfinding and memoization
	public MazeCellData data;

	/**
	 * Default constructor, sets the x and y coordinate of this cell.
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 */
	public MazeCell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Updates the data for this cell.
	 */
	public void updateData() {
		if (data.parent != null) {
			data.depth = (data.parent).data.depth + 1;
			data.cost = (data.parent).data.cost + 1;
		}
	}

	/**
	 * Returns the x position of this cell.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y position of this cell.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the parent of this cell, used in pathfinding.
	 */
	public void setParent(MazeCell parent) {
		data.parent = parent;
		data.depth = parent.data.depth + 1;
		data.cost = parent.data.cost + 1;
	}

	/**
	 * Returns the parent of this cell.
	 */
	public MazeCell getParent() {
		return data.parent;
	}

	/**
	 * Sets the state of this maze cell (passable, impassable, etc).
	 * @param state - the int representation used for the given state
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * Returns the String representation of this cell.
	 */
	public String toString() {
		return "MazeCell at " + x + ", " + y + " with state " + state;
	}

	/**
	 * Returns the int representation of the current state of this cell.
	 */
	public int getState() {
		return state;
	}

	public void setVisited(boolean visited) {
		data.visited = visited;
	}

	public boolean isVisited() {
		return data.visited;
	}

	public void setCost(int cost) {
		data.cost = cost;
	}

	public int getCost() {
		return data.cost;
	}
}