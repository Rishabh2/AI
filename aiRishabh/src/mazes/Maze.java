package mazes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Maze represents a 2D array consisting of MazeCells.
 * @author Keshav Saharia
 */

public class Maze {
	private MazeCell[][] maze;
	private MazeCell begin;
	private MazeCell target;
	private int currentTarget = -1, currentTargetNumber = 0;

	// Cell states
	public static final int PASSABLE = 1;
	public static final int IMPASSABLE = 2;
	public static final int BEGIN = 3;
	public static final int TARGET = 4;

	/**
	 * Empty constructor, defaults to a 10 x 10 maze.
	 */
	public Maze() {
		initialize(10, 10);
		initializeData();
	}

	/**
	 * Attempts to create a maze from the given maze file.
	 */
	public Maze(String file) {
		initializeFromFile(file);
		initializeData();
	}

	/**
	 * Constructor to specify width and height of the maze.
	 * @param width - width of the maze
	 * @param height - height of the maze
	 */
	public Maze(int width, int height) {
		initialize(width, height);
		initializeData();
	}

	/**
	 * Copy constructor.
	 */
	public Maze(Maze other) {
		initialize(other.getWidth(), other.getHeight());
		for (int i = 0 ; i < this.getWidth() ; i++) {
			for (int j = 0 ; j < this.getHeight() ; j++) {
				maze[i][j].setState(other.get(i, j).getState());
				maze[i][j].data = new MazeCellData(other.get(i, j).data);
			}
		}
	}

	/**
	 * All initialization logic for the maze.
	 */
	private void initialize(int width, int height) {
		maze = new MazeCell[width][height];
		for (int i = 0 ; i < width ; i++) {
			for (int j = 0 ; j < height ; j++) {
				maze[i][j] = new MazeCell(i, j);
				maze[i][j].setState(PASSABLE);
			}
		}
	}

	/**
	 * Initializes the data for each cell.
	 */
	private void initializeData() {
		for (int i = 0 ; i < getWidth() ; i++) {
			for (int j = 0 ; j < getHeight() ; j++) {
				maze[i][j].data = new MazeCellData(maze[i][j]);
				maze[i][j].data.neighbors = 
					maze[i][j].data.unvisitedNeighbors = getNeighbors(maze[i][j]).length;
			}
		}
	}

	/**
	 * Copies the cell data for another maze into this one.
	 */
	public void copyData(Maze other) {
		for (int i = 0 ; i < getWidth() ; i++) {
			for (int j = 0 ; j < getHeight() ; j++) {
				if (other.get(i, j) != null) 
					maze[i][j].data.copyData(other.get(i, j).data);
			}
		}
	}

	/**
	 * Updates the data for each cell.
	 */
	public void updateData() {
		for (int i = 0 ; i < getWidth() ; i++) {
			for (int j = 0 ; j < getHeight() ; j++) {
				maze[i][j].updateData();
			}
		}
	}

	/**
	 * Initializes the maze from a file.
	 */
	private void initializeFromFile(String file) {
		int width = 0, height = 0;
		StringBuilder input = new StringBuilder();
		try {
			Scanner s = new Scanner(new File(file));
			while (s.hasNextLine()) {
				input.append(s.nextLine());
				input.append("\n");
				height++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (input.length() > 0) {
			width = input.length() / height - 1;
			initialize(width, height);
		//	ArrayList <MazeCell> targets = new ArrayList <MazeCell> ();
			for (int i = 0 ; i < width ; i++) {
				for (int j = 0 ; j < height ; j++) {
					switch ( input.charAt(i + j * (width + 1)) ) {
					case '%': maze[i][j].setState(IMPASSABLE); break;
					case 'P': 
						maze[i][j].setState(BEGIN); 
						begin = maze[i][j];
					break;
					case '.': 
						maze[i][j].setState(TARGET); 
						target = maze[i][j];
					break;
					}
				}
			}
			//target = targets.toArray(new MazeCell[targets.size()]);
		}
	}

	/**
	 * Returns a String representation of this maze.
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0 ; i < getHeight() ; i++) {
			for (int j = 0 ; j < getWidth() ; j++) {
				switch(maze[j][i].getState()) {
				case PASSABLE:		
					if (maze[j][i].isVisited()) {
						str.append("x");
					} else {
						str.append(" ");
					} break;
				case IMPASSABLE: 	str.append("%"); break;
				case BEGIN:			str.append("P"); break;
				case TARGET:		str.append("."); break;
				}
				str.append(" ");
			}
			str.append("\n");
		}
		return str.toString();
	}

	/**
	 * Returns an array of neighbors of the given cell.
	 */
	public MazeCell[] getNeighbors(MazeCell cell) {
		if (cell.getState() == IMPASSABLE) return new MazeCell[0];
		int x = cell.getX(), y = cell.getY();
		int[] xpos = {x - 1, x, x + 1, x};
		int[] ypos = {y, y + 1, y, y -1};

		ArrayList <MazeCell> neighbors = new ArrayList <MazeCell> ();
		for (int i = 0 ; i <= xpos.length ; i++) {
			try {
				if (maze[xpos[i]][ypos[i]] != cell && maze[xpos[i]][ypos[i]].getState() != IMPASSABLE)
					neighbors.add(maze[xpos[i]][ypos[i]]);
			} catch (ArrayIndexOutOfBoundsException e) { }
		}
		return neighbors.toArray(new MazeCell[neighbors.size()]);
	}

	/**
	 * Returns the MazeCell at the given index, or null if the index is out of bounds.
	 */
	public MazeCell get(int i, int j) {
		try {
			return maze[i][j];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Marks the node at the given position.
	 */
	public void mark(int i, int j) {
		maze[i][j].data.visited = true;
	}

	/**
	 * Unmarks all non-target nodes.
	 */
	public void unmark() {
		for (int i = 0 ; i < getWidth() ; i++) {
			for (int j = 0 ; j < getHeight() ; j++) {
				maze[i][j].data.visited = false;
			}
		}
	}

	/**
	 * Sets the state of the cell at the given index.
	 */
	public void set(int i, int j, int state) {
		maze[i][j].setState(state);
	}

	/**
	 * Returns the maximum depth of any cell in the maze.
	 */
	public int maxDepth() {
		int max = 0;
		for (int i = 0 ; i < getWidth() ; i++)
			for (int j = 0 ; j < getHeight() ; j++)
				max = (maze[i][j].data.depth > max) ? maze[i][j].data.depth : max;
		return max;
	}

	/**
	 * Returns the width of this maze.
	 */
	public int getWidth() {
		return maze.length;
	}

	/**
	 * Returns the height of this maze.
	 */
	public int getHeight() {
		return maze[0].length;
	}

	/**
	 * Returns the target cell of this maze.
	 * @return
	 */
	public MazeCell getTarget() { 
		return target;
	}

	/**
	 * Returns the array of targets for this maze.
	 */
	//public MazeCell[] getTargets() {
		//return target;
//	}

	/**
	 * Sets the target of this maze to the nearest target cell
	 * to the given cell.
	 * @param current
	 */
	/*public void setNextTarget(MazeCell current) {
		if (getTarget() != null)
			getTarget().data.targetNumber = currentTargetNumber;
		currentTargetNumber++;
		currentTarget = getNearestTargetIndex(current);
	}*/

	/**
	 * Returns the index of the nearest target cell to the given
	 * cell.
	 */
	/*private int getNearestTargetIndex(MazeCell cell) {
		int index = -1;
		int minDistance = Integer.MAX_VALUE;
		for (int i = 0 ; i < target.length ; i++) {
			if ( (!target[i].data.targetReached) && target[i] != cell) {
				if (manhattanDistance(cell, target[i]) < minDistance) {
					index = i;
					minDistance = manhattanDistance(cell, target[i]);
				}
			} else {
			}
		}
		return index;

	}*/

	/**
	 * Returns the Manhattan distance between two cells.
	 */
	private int manhattanDistance(MazeCell begin, MazeCell end) {
		return Math.abs(begin.getX() - end.getX()) + Math.abs(begin.getY() - end.getY());
	}

	/**
	 * Sets the end cell of this maze to the given MazeCell instance.
	 */
	public MazeCell getBegin() {
		return begin;
	}
}